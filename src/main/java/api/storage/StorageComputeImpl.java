package main.java.api.storage;

import java.util.Collections;
import java.util.List;

public class StorageComputeImpl implements StorageComputeAPI {
    // Dependencies (might need to call computation later)
    private main.java.api.compute.ComputationAPI computationAPI;

    public StorageComputeImpl(main.java.api.compute.ComputationAPI computationAPI) {
        this.computationAPI = computationAPI;
    }

    @Override
    public DataBatch readData() {
        // Stub: return empty list
        return (DataBatch) Collections.emptyList();
    }

    @Override
    public boolean writeData(List<Integer> data) {
        // Stub: pretend to fail
        return false;
    }

	@Override
	public void writeData(DataBatch data) {
		// TODO Auto-generated method stub
		
	}
}
