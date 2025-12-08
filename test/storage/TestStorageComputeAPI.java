package storage;

import org.junit.jupiter.api.Test;
import user.DataSource;

import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import user.DataDestination;

class TestStorageComputeAPI {
    @Test
    void testWriteDataAndReadData() throws IOException {
        StorageComputeImpl storage = new StorageComputeImpl();
        Path tempFile = Files.createTempFile("test_storage_write", ".txt");
        DataDestination destination = Mockito.mock(DataDestination.class);
        Mockito.when(destination.getIdentifier()).thenReturn(tempFile.toString());
        String data = "12,34,56";
        boolean writeResult = storage.writeData(destination, data);
        assertTrue(writeResult);
        DataSource source = Mockito.mock(DataSource.class);
        Mockito.when(source.getIdentifier()).thenReturn(tempFile.toString());
        long readResult = storage.readData(source);
        assertTrue(readResult == 123456L);
        Files.deleteIfExists(tempFile);
    }

    @Test
    void testWriteDataNullArgs() {
        StorageComputeImpl storage = new StorageComputeImpl();
        assertFalse(storage.writeData(null, "data"));
        DataDestination destination = Mockito.mock(DataDestination.class);
        Mockito.when(destination.getIdentifier()).thenReturn(null);
        assertFalse(storage.writeData(destination, "data"));
        assertFalse(storage.writeData(destination, null));
    }
}