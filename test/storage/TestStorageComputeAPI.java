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

        // Use a guaranteed non-existent file path
        DataSource mockBatch = Mockito.mock(DataSource.class);
        Mockito.when(mockBatch.getIdentifier()).thenReturn("nonexistent_file_12345.txt");

        List<Integer> data = storage.readData(mockBatch);
        assertTrue(data.isEmpty(), "readData should return empty list for non-existent file");
    }

    @Test
    void testWriteDataReturnsFalseByDefault() {
        ComputationAPI mockComp = Mockito.mock(ComputationAPI.class);
        StorageComputeImpl storage = new StorageComputeImpl(mockComp);
        // Use an invalid path to ensure writeData fails
        user.DataDestination mockDestination = Mockito.mock(user.DataDestination.class);
        Mockito.when(mockDestination.getIdentifier()).thenReturn("Z:/invalid_path/FilePath");
        storage.setDestination(mockDestination);
        boolean result = storage.writeData(List.of(1, 2, 3));
        assertFalse(result, "writeData should return false for invalid path");
    }
}