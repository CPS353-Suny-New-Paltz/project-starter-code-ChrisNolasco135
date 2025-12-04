package user;

import java.util.Collections;
import java.util.List;

public class UserComputeImpl implements UserComputeAPI {
    // Dependencies (talking to storage)
    private storage.StorageComputeAPI storageAPI;
    private compute.ComputationAPI computeAPI;

    public UserComputeImpl(storage.StorageComputeAPI storageAPI, compute.ComputationAPI computeAPI) {
        this.storageAPI = storageAPI;
        this.computeAPI = computeAPI;
    }

    @Override
    public boolean submitJob(DataSource source, DataDestination destination, String delimiter) {
        setInputSource(source);
        setOutputDestination(destination);
        setDelimiters(delimiter);
        storageAPI.setSource(source);
        storageAPI.setDestination(destination);
        storageAPI.setDelimiter(delimiter);
        List<Integer> inputData = storageAPI.readData(source);
        if (inputData == null || inputData.isEmpty()) {
            return false;
        }
        List<Integer> results = computeAPI.processJob(inputData);
        if (results == null) {
            return false;
        }
        boolean writeSuccess = storageAPI.writeData(results);
        return writeSuccess;
    }

    @Override
    public List<String> getResults() {
        // Stub: return empty list
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
}