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
	public void setInputSource(DataSource source) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setOutputDestination(DataDestination destination) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDelimiters(String delimiter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executeJob() {
		// TODO Auto-generated method stub
		
	}
}
