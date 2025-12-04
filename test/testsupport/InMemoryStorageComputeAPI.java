package testsupport;


import storage.StorageComputeAPI;
import user.DataDestination;
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
        destination.getOutputData().clear(); // Clear previous output
        for (Integer num : data) {
            destination.addOutput(num.toString());
        }
        return true; // Always succeed in test mode
    }

	@Override
	public void setSource(DataSource source) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDestination(DataDestination destination) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDelimiter(String delimiter) {
		// TODO Auto-generated method stub
		
	}
}