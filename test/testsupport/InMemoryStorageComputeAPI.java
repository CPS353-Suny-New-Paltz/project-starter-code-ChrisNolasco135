package testsupport;

import storage.StorageComputeAPI;
import user.DataDestination;
import user.DataSource;

import java.util.List;

public class InMemoryStorageComputeAPI implements StorageComputeAPI {
    private final InMemoryDataSource source;
    private final InMemoryDataDestination destination;
    private String delimiter = ",";

    public InMemoryStorageComputeAPI(InMemoryDataSource source, InMemoryDataDestination destination) {
        this.source = source;
        this.destination = destination;
    }

    @Override
    public List<Integer> readData(DataSource data) {
        if (source == null || source.getInputData() == null || source.getInputData().isEmpty()) {
            throw new IllegalArgumentException("DataSource must not be null or empty");
        }
        return source.getInputData();
    }

    @Override
    public boolean writeData(DataDestination destination, String data) {
        return writeData(destination, data, ",");
    }

    @Override
    public boolean writeData(DataDestination destination, String data, String delimiter) {
        if (data == null || destination == null || delimiter == null || delimiter.trim().isEmpty()) {
            throw new IllegalArgumentException("Data, destination, and delimiter must not be null or empty");
        }
        try {
            testsupport.InMemoryDataDestination memDest = (testsupport.InMemoryDataDestination) destination;
            memDest.getOutputData().clear();
            for (int i = 0; i < data.length(); i++) {
                memDest.addOutput(String.valueOf(data.charAt(i)));
            }
            return true;
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Invalid DataDestination type: " + e.getMessage());
        }
    }
}