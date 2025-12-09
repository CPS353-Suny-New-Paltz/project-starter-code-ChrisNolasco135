package user;

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
        Future<Boolean> future = threadPool.submit(() ->
            UserComputeUtil.processJob(storageAPI, computeAPI, source, destination, delimiter)
        );
        try {
            return future.get();
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
