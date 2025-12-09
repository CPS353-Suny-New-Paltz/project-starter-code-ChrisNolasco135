package user;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import storage.StorageComputeAPI;
import compute.ComputationAPI;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

class TestUserComputeAPI {
    @Test
    void testSubmitJobSuccess() {
        StorageComputeAPI storageAPI = Mockito.mock(StorageComputeAPI.class);
        ComputationAPI computationAPI = Mockito.mock(ComputationAPI.class);
        DataSource source = Mockito.mock(DataSource.class);
        DataDestination destination = Mockito.mock(DataDestination.class);
        List<Integer> inputData = Arrays.asList(7, 8); // Encoded for "AB"
        Mockito.when(storageAPI.readData(source)).thenReturn(inputData);
        Mockito.when(computationAPI.compute(inputData)).thenReturn("AB");
        Mockito.when(storageAPI.writeData(destination, "AB", ",")).thenReturn(true);
        UserComputeImpl userCompute = new UserComputeImpl(storageAPI, computationAPI);
        boolean result = userCompute.submitJob(source, destination, ",");
        assertTrue(result);
        Mockito.verify(storageAPI).readData(source);
        Mockito.verify(computationAPI).compute(inputData);
        Mockito.verify(storageAPI).writeData(destination, "AB", ",");
    }

    @Test
    void testSubmitJobWriteFails() {
        StorageComputeAPI storageAPI = Mockito.mock(StorageComputeAPI.class);
        ComputationAPI computationAPI = Mockito.mock(ComputationAPI.class);
        DataSource source = Mockito.mock(DataSource.class);
        DataDestination destination = Mockito.mock(DataDestination.class);
        List<Integer> inputData = Arrays.asList(7, 8);
        Mockito.when(storageAPI.readData(source)).thenReturn(inputData);
        Mockito.when(computationAPI.compute(inputData)).thenReturn("AB");
        Mockito.when(storageAPI.writeData(destination, "AB", ",")).thenReturn(false);
        UserComputeImpl userCompute = new UserComputeImpl(storageAPI, computationAPI);
        boolean result = userCompute.submitJob(source, destination, ",");
        assertFalse(result);
    }

    @Test
    void testSubmitJobThrowsOnNullSource() {
        StorageComputeAPI storageAPI = Mockito.mock(StorageComputeAPI.class);
        ComputationAPI computationAPI = Mockito.mock(ComputationAPI.class);
        DataDestination destination = Mockito.mock(DataDestination.class);
        UserComputeImpl userCompute = new UserComputeImpl(storageAPI, computationAPI);
        assertThrows(IllegalArgumentException.class, () -> userCompute.submitJob(null, destination, ","));
    }

    @Test
    void testSubmitJobThrowsOnNullDestination() {
        StorageComputeAPI storageAPI = Mockito.mock(StorageComputeAPI.class);
        ComputationAPI computationAPI = Mockito.mock(ComputationAPI.class);
        DataSource source = Mockito.mock(DataSource.class);
        UserComputeImpl userCompute = new UserComputeImpl(storageAPI, computationAPI);
        assertThrows(IllegalArgumentException.class, () -> userCompute.submitJob(source, null, ","));
    }

    @Test
    void testSubmitJobThrowsOnNullDelimiter() {
        StorageComputeAPI storageAPI = Mockito.mock(StorageComputeAPI.class);
        ComputationAPI computationAPI = Mockito.mock(ComputationAPI.class);
        DataSource source = Mockito.mock(DataSource.class);
        DataDestination destination = Mockito.mock(DataDestination.class);
        UserComputeImpl userCompute = new UserComputeImpl(storageAPI, computationAPI);
        assertThrows(IllegalArgumentException.class, () -> userCompute.submitJob(source, destination, null));
    }

    @Test
    void testSetDelimiterValid() {
        StorageComputeAPI storageAPI = Mockito.mock(StorageComputeAPI.class);
        ComputationAPI computationAPI = Mockito.mock(ComputationAPI.class);
        UserComputeImpl userCompute = new UserComputeImpl(storageAPI, computationAPI);
        String delimiter = userCompute.setDelimiter("|");
        assertEquals("|", delimiter);
    }

    @Test
    void testSetDelimiterThrowsOnNullOrEmpty() {
        StorageComputeAPI storageAPI = Mockito.mock(StorageComputeAPI.class);
        ComputationAPI computationAPI = Mockito.mock(ComputationAPI.class);
        UserComputeImpl userCompute = new UserComputeImpl(storageAPI, computationAPI);
        assertThrows(IllegalArgumentException.class, () -> userCompute.setDelimiter(null));
        assertThrows(IllegalArgumentException.class, () -> userCompute.setDelimiter("   "));
    }
}