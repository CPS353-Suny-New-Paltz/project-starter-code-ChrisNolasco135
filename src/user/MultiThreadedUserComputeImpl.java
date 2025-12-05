package user;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class MultiThreadedUserComputeImpl implements UserComputeAPI {
    private static final int MAX_THREADS = 4; 
    private storage.StorageComputeAPI storageAPI;
    private compute.ComputationAPI computeAPI;
    private final ExecutorService executor;

    public MultiThreadedUserComputeImpl(storage.StorageComputeAPI storageAPI, compute.ComputationAPI computeAPI) {
        this.storageAPI = storageAPI;
        this.computeAPI = computeAPI;
        this.executor=Executors.newFixedThreadPool(MAX_THREADS);
    }

    @Override
    public boolean submitJob(DataSource source, DataDestination destination, String delimiter) {
        setInputSource(source);
        setOutputDestination(destination);
        setDelimiters(delimiter);
        List<Integer> inputData = storageAPI.readData(source);
        if (inputData == null || inputData.isEmpty()) {
            return false;
        }
        int partitionSize = (int) Math.ceil(inputData.size() / (double) MAX_THREADS);
        List<Future<List<Integer>>> futures = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(MAX_THREADS);
        for (int i = 0; i < inputData.size(); i += partitionSize) {
            int start = i;
            int end = Math.min(i + partitionSize, inputData.size());
            List<Integer> partition = inputData.subList(start, end);
            futures.add(executor.submit(() -> computeAPI.processJob(partition)));
        }
        List<Integer> combinedResults = new ArrayList<>();
        try {
            for (Future<List<Integer>> future : futures) {
                List<Integer> result = future.get();
                if (result != null) {
                    combinedResults.addAll(result);
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            executor.shutdown();
            return false;
        }
        executor.shutdown();
        boolean writeSuccess = storageAPI.writeData(combinedResults);
        return writeSuccess;
    }

    @Override
    public List<String> getResults() {
        return Collections.emptyList();
    }

    @Override
    public DataSource setInputSource(DataSource source) {
        return source;
    }

    @Override
    public DataDestination setOutputDestination(DataDestination destination) {
        return destination;
    }

    @Override
    public String setDelimiters(String delimiter) {
        return delimiter;
    }

    @Override
    public DataSource executeJob(DataSource source) {
        return null;
    }

	public void shutdown() {
		executor.shutdown();
		
	}
}
