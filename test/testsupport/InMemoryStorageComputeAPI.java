package testsupport;


import storage.StorageComputeAPI;
import user.DataSource;

import java.util.List;

public class InMemoryStorageComputeAPI implements StorageComputeAPI {
    private final InMemoryDataSource source;
    private final InMemoryDataDestination destination;

    public InMemoryStorageComputeAPI(InMemoryDataSource source, InMemoryDataDestination destination) {
        this.source = source;
        this.destination = destination;
    }

    @Override
    public List<Integer> readData(DataSource data) {
        // Just return the in-memory input list;
        return source.getInputData();
    }

    @Override
    public boolean writeData(List<Integer> data) {
        // Convert integers to strings and append to output list
        for (Integer num : data) {
            destination.addOutput(num.toString());
        }
        return true; // Always succeed in test mode
    }
}