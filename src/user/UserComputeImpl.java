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
        setDelimiter(delimiter);
        return UserComputeUtil.processJob(storageAPI, computeAPI, source, destination, delimiter);
    }
    
    public String setDelimiter(String delimiter) {
        if (delimiter == null || delimiter.trim().isEmpty()) {
            throw new IllegalArgumentException("Delimiter must not be null or empty");
        }
        this.delimiter = delimiter;
        return this.delimiter;
    }
}