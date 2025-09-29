package com.project.api.storage;

import project.annotations.ProcessAPI;

@ProcessAPI
public interface StorageComputeAPI {
	DataBatch readData();         // storage → compute
    void writeData(DataBatch data); // compute → storage
}
