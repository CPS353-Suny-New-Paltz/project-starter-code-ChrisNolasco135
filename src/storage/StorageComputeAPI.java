package storage;

import project.annotations.ProcessAPI;
import user.DataSource;
import user.DataDestination;

@ProcessAPI
public interface StorageComputeAPI {
	long readData(DataSource source);         // storage → compute
    boolean writeData(DataDestination destination, String data); // compute → storage
	
}