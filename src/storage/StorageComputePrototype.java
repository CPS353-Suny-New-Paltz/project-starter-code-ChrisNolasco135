package storage;

import project.annotations.ProcessAPIPrototype;
import java.util.List;

public class StorageComputePrototype {
	@ProcessAPIPrototype
	public void prototype(StorageComputeAPI storageComputeAPI) {
		// Example DataBatch implementation
		DataBatch batch = new DataBatch() {
			@Override
			public int[] getData() {
				return new int[] {1, 2, 3, 4, 5}; // sample data
			}
			@Override
			public boolean isEmpty() {
				return false;
			}
		};

		// Read data from storage
		List<Integer> readResult = storageComputeAPI.readData(batch);
		System.out.println("Read data from DataBatch:");
		for (int value : readResult) {
			System.out.println(value);
		}

		// Write data to storage
		List<Integer> writeData = java.util.Arrays.asList(10, 20, 30);
		boolean writeSuccess = storageComputeAPI.writeData(writeData);
		System.out.println("Write data result: " + writeSuccess);
	}
	
	public java.util.List<Integer> readData(DataBatch data) {
		return readData(data);
	}
	
	public boolean writeData(List<Integer> data) {
		return writeData(data);
	}
}