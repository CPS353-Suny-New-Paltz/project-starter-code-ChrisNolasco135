package storage;

import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestStorageComputeAPI {
    @Test
    void testReadDataWithMockedAPI() {
        // Mock StorageComputeAPI
        StorageComputeAPI mockAPI = Mockito.mock(StorageComputeAPI.class);
        // Mock DataBatch
        DataBatch mockBatch = Mockito.mock(DataBatch.class);
        Mockito.when(mockBatch.isEmpty()).thenReturn(false);
        Mockito.when(mockBatch.getData()).thenReturn(new int[] {1, 2, 3});
        Mockito.when(mockAPI.readData(mockBatch)).thenReturn(Arrays.asList(1, 2, 3));

        List<Integer> result = mockAPI.readData(mockBatch);
        assertEquals(Arrays.asList(1, 2, 3), result, "readData should return the mocked list");
    }

    @Test
    void testWriteDataWithMockedAPI() {
        // Mock StorageComputeAPI
        StorageComputeAPI mockAPI = Mockito.mock(StorageComputeAPI.class);
        List<Integer> data = Arrays.asList(1, 2, 3);
        Mockito.when(mockAPI.writeData(data)).thenReturn(true);

        boolean result = mockAPI.writeData(data);
        assertTrue(result, "writeData should return true as mocked");
    }
}