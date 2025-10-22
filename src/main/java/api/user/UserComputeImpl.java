package main.java.api.user;

import java.util.Collections;

import java.util.List;

public class UserComputeImpl implements UserComputeAPI {
    // Dependencies (talking to storage)
    private main.java.api.storage.StorageComputeAPI storageAPI;

    public UserComputeImpl(main.java.api.storage.StorageComputeAPI storageAPI) {
        this.storageAPI = storageAPI;
    }

    @Override
    public boolean submitJob(DataSource source, DataDestination destination, String delimiter) {
        // Stub: return failure for now
        return false;
    }

    @Override
    public List<String> getResults() {
        // Stub: return empty list
        return Collections.emptyList();
    }

	@Override
	public DataSource setInputSource(DataSource source) {
		return source;
		// TODO Auto-generated method stub
		
	}

	@Override
	public DataDestination setOutputDestination(DataDestination destination) {
		return destination;
		// TODO Auto-generated method stub
		
	}

	@Override
	public String setDelimiters(String delimiter) {
		return delimiter;
		
	}

	@Override
	public DataSource executeJob(DataSource source) {
		// TODO Auto-generated method stub
		return null;
	}
}
