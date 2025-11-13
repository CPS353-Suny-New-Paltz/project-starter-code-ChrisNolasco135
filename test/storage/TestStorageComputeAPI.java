package storage;

import org.junit.jupiter.api.Test;
import compute.ComputationAPI;
import user.DataSource;

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
        DataSource mockBatch = Mockito.mock(DataSource.class);
        Mockito.when(mockBatch.getIdentifier()).thenReturn("FilePath");

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