package user;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutionException;

public class UserComputeMultiThreadedImpl implements UserComputeAPI {
    private static final int MAX_THREADS = 4; // Documented upper bound
    private final storage.StorageComputeAPI storageAPI;
    private final compute.ComputationAPI computeAPI;
    private final ExecutorService threadPool;
    private String delimiter = ",";

    public UserComputeMultiThreadedImpl(storage.StorageComputeAPI storageAPI, compute.ComputationAPI computeAPI) {
        this.storageAPI = storageAPI;
        this.computeAPI = computeAPI;
        this.threadPool = Executors.newFixedThreadPool(MAX_THREADS);
    }

    @Override
    public boolean submitJob(DataSource source, DataDestination destination, String delimiter) {
        setDelimiter(delimiter);
        // Read input as List<Integer> from DataSource
        List<Integer> inputData = storageAPI.readData(source);
        if (inputData == null || inputData.isEmpty()) {
            throw new IllegalArgumentException("Input data must not be empty");
        }
        try {
            // Submit a decoding task for each integer
            List<Future<String>> futures = new ArrayList<>();
            for (Integer encodedValue : inputData) {
                futures.add(threadPool.submit(() -> {
                    if (encodedValue == null) {
                    	return "";
                    }
                    int shift = 7;
                    int mod = 27;
                    int decodedValue = (encodedValue - shift + mod) % mod;
                    char letter = (decodedValue == 26) ? ' ' : (char) ('A' + decodedValue);
                    return String.valueOf(letter);
                }));
            }
            // Collect results in order
            List<String> decodedList = new ArrayList<>();
            for (Future<String> future : futures) {
                decodedList.add(future.get());
            }
            // Join results with delimiter
            String result = String.join(delimiter, decodedList);
            // Write the result as a single line
            return storageAPI.writeData(destination, result, delimiter);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Job execution failed", e);
        }
    }

    public String setDelimiter(String delimiter) {
        if (delimiter == null || delimiter.trim().isEmpty()) {
            throw new IllegalArgumentException("Delimiter must not be null or empty");
        }
        this.delimiter = delimiter;
        return this.delimiter;
    }

    public void shutdown() {
        threadPool.shutdown();
    }
}