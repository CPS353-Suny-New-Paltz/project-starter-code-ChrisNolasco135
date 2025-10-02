package main.java.api.storage;

import java.util.List;

import project.annotations.ProcessAPI;

@ProcessAPI
public interface StorageComputeAPI {
	DataBatch readData();         // storage → compute
    void writeData(DataBatch data); // compute → storage
	boolean writeData(List<Integer> data);
}
