package test.junittests.java.testsupport;

import main.java.api.storage.StorageComputeAPI;
import main.java.api.storage.DataBatch;

import java.util.List;

public class InMemoryStorageComputeAPI implements StorageComputeAPI {
    private final InMemoryDataSource source;
    private final InMemoryDataDestination destination;

    public InMemoryStorageComputeAPI(InMemoryDataSource source, InMemoryDataDestination destination) {
        this.source = source;
        this.destination = destination;
    }

    @Override
    public DataBatch readData() {
        // Just return the in-memory input list
        return source.getInputData();
    }

    @Override
    public boolean writeData(DataBatch data) {
        // Convert integers to strings and append to output list
        for (Integer num : data) {
            destination.addOutput(num.toString());
        }
        return true; // Always succeed in test mode
    }
}
