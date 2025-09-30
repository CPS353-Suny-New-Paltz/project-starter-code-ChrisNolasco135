package main.java.api.storage;

import main.java.project.annotations.ProcessAPI;

@ProcessAPI
public interface StorageComputeAPI {
	DataBatch readData();         // storage → compute
    void writeData(DataBatch data); // compute → storage
}
