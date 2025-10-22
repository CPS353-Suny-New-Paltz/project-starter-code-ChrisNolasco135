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

        // Use Mockito to mock DataBatch
        DataBatch mockBatch = Mockito.mock(DataBatch.class);
        Mockito.when(mockBatch.isEmpty()).thenReturn(true);
        Mockito.when(mockBatch.getData()).thenReturn(new int[0]);

        List<Integer> data = storage.readData(mockBatch);
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