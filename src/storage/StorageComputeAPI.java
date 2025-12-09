package storage;

import java.util.List;

import project.annotations.ProcessAPI;
import user.DataSource;
import user.DataDestination;

@ProcessAPI
public interface StorageComputeAPI {
	List<Integer> readData(DataSource source);         // storage → compute
    boolean writeData(DataDestination destination, String data); // compute → storage
    boolean writeData(DataDestination destination, String data, String delimiter); // compute → storage with custom delimiter
	
}