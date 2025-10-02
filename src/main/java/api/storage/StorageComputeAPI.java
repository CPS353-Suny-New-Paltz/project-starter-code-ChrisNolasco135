package main.java.api.storage;

import java.util.List;


import project.annotations.ProcessAPI;

@ProcessAPI
public interface StorageComputeAPI {
	List<Integer> readData();         // storage → compute
    boolean writeData(List<Integer> data); // compute → storage
}
