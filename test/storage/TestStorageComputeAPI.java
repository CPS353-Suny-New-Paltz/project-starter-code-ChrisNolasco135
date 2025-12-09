package storage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mockito;
import user.DataSource;
import user.DataDestination;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Arrays;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestStorageComputeAPI {
    @Test
    void testReadDataReadsIntegersFromFile(@TempDir Path tempDir) throws IOException {
        Path file = tempDir.resolve("input.txt");
        Files.write(file, "1,2,3,4,5".getBytes());
        DataSource source = Mockito.mock(DataSource.class);
        Mockito.when(source.getIdentifier()).thenReturn(file.toString());
        StorageComputeAPI api = new StorageComputeImpl();
        List<Integer> result = api.readData(source);
        assertEquals(Arrays.asList(1,2,3,4,5), result);
    }

    @Test
    void testWriteDataWritesCommaSeparatedString(@TempDir Path tempDir) throws IOException {
        Path file = tempDir.resolve("output.txt");
        DataDestination dest = Mockito.mock(DataDestination.class);
        Mockito.when(dest.getIdentifier()).thenReturn(file.toString());
        StorageComputeAPI api = new StorageComputeImpl();
        boolean success = api.writeData(dest, "HELLO");
        assertTrue(success);
        String content = Files.readString(file);
        assertEquals("H,E,L,L,O", content);
    }

    @Test
    void testWriteDataWithCustomDelimiter(@TempDir Path tempDir) throws IOException {
        Path file = tempDir.resolve("output2.txt");
        DataDestination dest = Mockito.mock(DataDestination.class);
        Mockito.when(dest.getIdentifier()).thenReturn(file.toString());
        StorageComputeAPI api = new StorageComputeImpl();
        boolean success = api.writeData(dest, "WORLD", "|");
        assertTrue(success);
        String content = Files.readString(file);
        assertEquals("W|O|R|L|D", content);
    }

    @Test
    void testReadDataHandlesInvalidIntegers(@TempDir Path tempDir) throws IOException {
        Path file = tempDir.resolve("input2.txt");
        Files.write(file, "1,abc,3".getBytes());
        DataSource source = Mockito.mock(DataSource.class);
        Mockito.when(source.getIdentifier()).thenReturn(file.toString());
        StorageComputeAPI api = new StorageComputeImpl();
        List<Integer> result = api.readData(source);
        assertEquals(Arrays.asList(1,3), result);
    }

    @Test
    void testWriteDataThrowsOnNullDestination() {
        StorageComputeAPI api = new StorageComputeImpl();
        assertThrows(IllegalArgumentException.class, () -> api.writeData(null, "DATA"));
    }

    @Test
    void testWriteDataThrowsOnNullData() {
        StorageComputeAPI api = new StorageComputeImpl();
        DataDestination dest = Mockito.mock(DataDestination.class);
        Mockito.when(dest.getIdentifier()).thenReturn("dummy.txt");
        assertThrows(IllegalArgumentException.class, () -> api.writeData(dest, null));
    }

    @Test
    void testWriteDataWithDelimiterThrowsOnNullDelimiter() {
        StorageComputeAPI api = new StorageComputeImpl();
        DataDestination dest = Mockito.mock(DataDestination.class);
        Mockito.when(dest.getIdentifier()).thenReturn("dummy.txt");
        assertThrows(IllegalArgumentException.class, () -> api.writeData(dest, "DATA", null));
    }
}