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
    	try {
            // Stub: return empty list
            return Collections.emptyList();
        } catch (Exception e) {
            System.err.println("getResults error: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public DataSource setInputSource(DataSource source) {
    	try {
			if (source == null){
				throw new IllegalArgumentException("DataSource must not be null");
			}
			return source;
		} catch (Exception e) {
			System.err.println("setInputSource error: " + e.getMessage());
			return null;
		}
	}

    @Override
    public DataDestination setOutputDestination(DataDestination destination) {
    	try {
			if (destination == null){
				throw new IllegalArgumentException("DataDestination must not be null");
			}
			return destination;
		} catch (Exception e) {
			System.err.println("setOutputDestination error: " + e.getMessage());
			return null;
		}
	}

    @Override
    public String setDelimiters(String delimiter) {
    	try {
			if (delimiter == null || delimiter.isEmpty()){
				throw new IllegalArgumentException("Delimiter must not be null or empty");
			}
			return delimiter;
		} catch (Exception e) {
			System.err.println("setDelimiters error: " + e.getMessage());
			return ",";
		}
	}

    @Override
    public DataSource executeJob(DataSource source) {
    	try {
			if (source == null){
				throw new IllegalArgumentException("DataSource must not be null");
			}
			// TODO Auto-generated method stub
			return null;
		} catch (Exception e) {
			System.err.println("executeJob error: " + e.getMessage());
			return null;
		}
	}
}