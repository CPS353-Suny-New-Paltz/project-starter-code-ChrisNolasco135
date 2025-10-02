package main.java.api.storage;

import java.util.ArrayList;
import java.util.List;

public class StorageComputeImpl implements StorageComputeAPI {
    // Dependencies (might need to call computation later)
    private main.java.api.compute.ComputationAPI computationAPI;

    public StorageComputeImpl(main.java.api.compute.ComputationAPI computationAPI) {
        this.computationAPI = computationAPI;
    }

    @Override
    public List<Integer> readData() {
        // Stub: return empty list
        return new ArrayList<>();
    }

    @Override
    public boolean writeData(List<Integer> data) {
        // Stub: pretend to fail
        return false;
    }

}
