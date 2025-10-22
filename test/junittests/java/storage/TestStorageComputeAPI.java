package test.junittests.java.storage;

import main.java.api.compute.ComputationAPI;


import main.java.api.storage.DataBatch;
import main.java.api.storage.StorageComputeImpl;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TestStorageComputeAPI {

    @Test
    void testReadDataReturnsEmptyList() {
        ComputationAPI mockComp = Mockito.mock(ComputationAPI.class);
        StorageComputeImpl storage = new StorageComputeImpl(mockComp);

        List<Integer> data = storage.readData(new DataBatch());
        assertTrue(data.isEmpty(), "Default readData should return empty list");
    }

    @Test
    void testWriteDataReturnsFalseByDefault() {
        ComputationAPI mockComp = Mockito.mock(ComputationAPI.class);
        StorageComputeImpl storage = new StorageComputeImpl(mockComp);

        boolean result = storage.writeData(List.of(1, 2, 3));
        assertFalse(result, "Default writeData should return false");
    }
}
