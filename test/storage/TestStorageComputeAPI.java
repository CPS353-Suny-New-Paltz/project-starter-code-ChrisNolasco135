package storage;

import org.junit.jupiter.api.Test;
import compute.ComputationAPI;
import user.DataSource;

import org.mockito.Mockito;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import user.DataDestination;

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

    @Test
    void testReadDataFromValidFile() throws IOException {
        ComputationAPI mockComp = Mockito.mock(ComputationAPI.class);
        StorageComputeImpl storage = new StorageComputeImpl(mockComp);
        Path tempFile = Files.createTempFile("testdata", ".txt");
        Files.writeString(tempFile, "1,2;3 4\t5");
        DataSource mockBatch = Mockito.mock(DataSource.class);
        Mockito.when(mockBatch.getIdentifier()).thenReturn(tempFile.toString());
        List<Integer> data = storage.readData(mockBatch);
        assertTrue(data.containsAll(List.of(1,2,3,4,5)), "readData should parse all integers");
        Files.deleteIfExists(tempFile);
    }

    @Test
    void testReadDataFromEmptyFile() throws IOException {
        ComputationAPI mockComp = Mockito.mock(ComputationAPI.class);
        StorageComputeImpl storage = new StorageComputeImpl(mockComp);
        Path tempFile = Files.createTempFile("emptydata", ".txt");
        DataSource mockBatch = Mockito.mock(DataSource.class);
        Mockito.when(mockBatch.getIdentifier()).thenReturn(tempFile.toString());
        List<Integer> data = storage.readData(mockBatch);
        assertTrue(data.isEmpty(), "readData should return empty list for empty file");
        Files.deleteIfExists(tempFile);
    }

    @Test
    void testReadDataWithInvalidIntegers() throws IOException {
        ComputationAPI mockComp = Mockito.mock(ComputationAPI.class);
        StorageComputeImpl storage = new StorageComputeImpl(mockComp);
        Path tempFile = Files.createTempFile("invaliddata", ".txt");
        Files.writeString(tempFile, "1,abc,2");
        DataSource mockBatch = Mockito.mock(DataSource.class);
        Mockito.when(mockBatch.getIdentifier()).thenReturn(tempFile.toString());
        // Should throw NumberFormatException, so only valid integers are added
        List<Integer> data = storage.readData(mockBatch);
        assertTrue(data.contains(1) && data.contains(2), "readData should skip invalid integers");
        Files.deleteIfExists(tempFile);
    }

    @Test
    void testWriteDataToValidFile() throws IOException {
        ComputationAPI mockComp = Mockito.mock(ComputationAPI.class);
        StorageComputeImpl storage = new StorageComputeImpl(mockComp);
        Path tempFile = Files.createTempFile("writedata", ".txt");
        DataDestination mockDest = Mockito.mock(DataDestination.class);
        Mockito.when(mockDest.getIdentifier()).thenReturn(tempFile.toString());
        storage.setDestination(mockDest);
        boolean result = storage.writeData(List.of(10, 20, 30));
        assertTrue(result, "writeData should succeed for valid file");
        String written = Files.readString(tempFile).trim();
        assertTrue(written.equals("10,20,30"), "writeData should write comma-separated values");
        Files.deleteIfExists(tempFile);
    }

    @Test
    void testWriteDataWithEmptyList() throws IOException {
        ComputationAPI mockComp = Mockito.mock(ComputationAPI.class);
        StorageComputeImpl storage = new StorageComputeImpl(mockComp);
        Path tempFile = Files.createTempFile("writeempty", ".txt");
        DataDestination mockDest = Mockito.mock(DataDestination.class);
        Mockito.when(mockDest.getIdentifier()).thenReturn(tempFile.toString());
        storage.setDestination(mockDest);
        boolean result = storage.writeData(List.of());
        assertTrue(result, "writeData should succeed for empty list");
        String written = Files.readString(tempFile).trim();
        assertTrue(written.isEmpty(), "writeData should write empty string for empty list");
        Files.deleteIfExists(tempFile);
    }

    @Test
    void testWriteDataWithCustomDelimiter() throws IOException {
        ComputationAPI mockComp = Mockito.mock(ComputationAPI.class);
        StorageComputeImpl storage = new StorageComputeImpl(mockComp);
        Path tempFile = Files.createTempFile("writedelim", ".txt");
        DataDestination mockDest = Mockito.mock(DataDestination.class);
        Mockito.when(mockDest.getIdentifier()).thenReturn(tempFile.toString());
        storage.setDestination(mockDest);
        storage.setDelimiter(";");
        boolean result = storage.writeData(List.of(1,2,3));
        assertTrue(result, "writeData should succeed with custom delimiter");
        String written = Files.readString(tempFile).trim();
        assertTrue(written.equals("1;2;3"), "writeData should use custom delimiter");
        Files.deleteIfExists(tempFile);
    }

    @Test
    void testWriteDataWithNoDestination() {
        ComputationAPI mockComp = Mockito.mock(ComputationAPI.class);
        StorageComputeImpl storage = new StorageComputeImpl(mockComp);
        boolean result = storage.writeData(List.of(1,2,3));
        assertFalse(result, "writeData should fail if destination is not set");
    }

    @Test
    void testSetSourceAndDestination() {
        ComputationAPI mockComp = Mockito.mock(ComputationAPI.class);
        StorageComputeImpl storage = new StorageComputeImpl(mockComp);
        DataSource mockSource = Mockito.mock(DataSource.class);
        DataDestination mockDest = Mockito.mock(DataDestination.class);
        storage.setSource(mockSource);
        storage.setDestination(mockDest);
        // No assertion needed, just ensure no exception
    }

    @Test
    void testSetDelimiter() {
        ComputationAPI mockComp = Mockito.mock(ComputationAPI.class);
        StorageComputeImpl storage = new StorageComputeImpl(mockComp);
        storage.setDelimiter("|");
        // No assertion needed, just ensure no exception
    }
}