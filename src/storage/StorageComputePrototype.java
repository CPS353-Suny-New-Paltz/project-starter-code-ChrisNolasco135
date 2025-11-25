package storage;

import project.annotations.ProcessAPIPrototype;
import user.DataSource;

import java.util.List;

public class StorageComputePrototype {
	@ProcessAPIPrototype
	public void prototype(StorageComputeAPI storageComputeAPI) {
		// Example DataBatch implementation
		DataSource source = new DataSource() {
			@Override
			public String getIdentifier() {
				return "filePath.txt";
			}
		};

		// Read data from storage
		List<Integer> readResult = storageComputeAPI.readData(source);
		System.out.println("Read data from DataSource:");
		for (int value : readResult) {
			System.out.println(value);
		}

		// Write data to storage
		List<Integer> writeData = java.util.Arrays.asList(10, 20, 30);
		boolean writeSuccess = storageComputeAPI.writeData(writeData);
		System.out.println("Write data result: " + writeSuccess);
	}
	
	public java.util.List<Integer> readData(DataSource data) {
		return readData(data);
	}
	
	public boolean writeData(List<Integer> data) {
		return writeData(data);
	}
}