package user;

import java.util.List;

public class UserComputeImpl implements UserComputeAPI {
    // Dependencies (talking to storage)
    private storage.StorageComputeAPI storageAPI;
    private compute.ComputationAPI computeAPI;
    private String delimiter = ",";
    
    public UserComputeImpl(storage.StorageComputeAPI storageAPI, compute.ComputationAPI computeAPI) {
        this.storageAPI = storageAPI;
        this.computeAPI = computeAPI;
    }

    @Override
    public boolean submitJob(DataSource source, DataDestination destination, String delimiter) {
        if (source == null || destination == null || delimiter == null) {
            throw new IllegalArgumentException("DataSource, DataDestination or Delimiter must not be null");
        }
        setDelimiter(delimiter);
        // Read input as List<Integer> from DataSource
        List<Integer> inputData = storageAPI.readData(source);
        // Compute the result using the full list
        String result = computeAPI.compute(inputData);
        // Write the result as a single line, separated by the delimiter
        boolean writeSuccess = storageAPI.writeData(destination, result, delimiter);
        return writeSuccess;
    }
    
    public String setDelimiter(String delimiter) {
        if (delimiter == null || delimiter.trim().isEmpty()) {
            throw new IllegalArgumentException("Delimiter must not be null or empty");
        }
        this.delimiter = delimiter;
        return this.delimiter;
    }
}