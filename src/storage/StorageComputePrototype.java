package storage;

import java.util.List;

import project.annotations.ProcessAPIPrototype;
import user.DataSource;
import user.DataDestination;

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
		
		DataDestination destination = new DataDestination() {
			@Override
			public String getIdentifier() {
				return "outputFilePath.txt";
			}
		};

		// Read data from storage
		List<Integer> readResult = storageComputeAPI.readData(source);
		System.out.println("Read data from DataSource: " + readResult);

		// Write data to storage
		String writeData = "HELLO WORLD";
		boolean writeSuccess = storageComputeAPI.writeData(destination, writeData);
		System.out.println("Write data result: " + writeSuccess);

	}
}