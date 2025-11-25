package user;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TestUserComputeAPI {
    @Test
    void testSubmitJobWithValidInput() {
        storage.StorageComputeAPI storageAPI = Mockito.mock(storage.StorageComputeAPI.class);
        compute.ComputationAPI computeAPI = Mockito.mock(compute.ComputationAPI.class);
        UserComputeImpl userCompute = new UserComputeImpl(storageAPI, computeAPI);
        DataSource source = Mockito.mock(DataSource.class);
        DataDestination dest = Mockito.mock(DataDestination.class);
        Mockito.when(storageAPI.readData(source)).thenReturn(List.of(1,2,3));
        Mockito.when(computeAPI.processJob(List.of(1,2,3))).thenReturn(List.of(4,5,6));
        Mockito.when(storageAPI.writeData(List.of(4,5,6))).thenReturn(true);
        boolean result = userCompute.submitJob(source, dest, ",");
        assertTrue(result, "submitJob should return true for valid input");
    }

    @Test
    void testSubmitJobWithEmptyInputData() {
        storage.StorageComputeAPI storageAPI = Mockito.mock(storage.StorageComputeAPI.class);
        compute.ComputationAPI computeAPI = Mockito.mock(compute.ComputationAPI.class);
        UserComputeImpl userCompute = new UserComputeImpl(storageAPI, computeAPI);
        DataSource source = Mockito.mock(DataSource.class);
        DataDestination dest = Mockito.mock(DataDestination.class);
        Mockito.when(storageAPI.readData(source)).thenReturn(Collections.emptyList());
        boolean result = userCompute.submitJob(source, dest, ",");
        assertFalse(result, "submitJob should return false for empty input data");
    }

    @Test
    void testSubmitJobWithNullInputData() {
        storage.StorageComputeAPI storageAPI = Mockito.mock(storage.StorageComputeAPI.class);
        compute.ComputationAPI computeAPI = Mockito.mock(compute.ComputationAPI.class);
        UserComputeImpl userCompute = new UserComputeImpl(storageAPI, computeAPI);
        DataSource source = Mockito.mock(DataSource.class);
        DataDestination dest = Mockito.mock(DataDestination.class);
        Mockito.when(storageAPI.readData(source)).thenReturn(null);
        boolean result = userCompute.submitJob(source, dest, ",");
        assertFalse(result, "submitJob should return false for null input data");
    }

    @Test
    void testSubmitJobWithNullResults() {
        storage.StorageComputeAPI storageAPI = Mockito.mock(storage.StorageComputeAPI.class);
        compute.ComputationAPI computeAPI = Mockito.mock(compute.ComputationAPI.class);
        UserComputeImpl userCompute = new UserComputeImpl(storageAPI, computeAPI);
        DataSource source = Mockito.mock(DataSource.class);
        DataDestination dest = Mockito.mock(DataDestination.class);
        Mockito.when(storageAPI.readData(source)).thenReturn(List.of(1,2,3));
        Mockito.when(computeAPI.processJob(List.of(1,2,3))).thenReturn(null);
        boolean result = userCompute.submitJob(source, dest, ",");
        assertFalse(result, "submitJob should return false if processJob returns null");
    }

    @Test
    void testSubmitJobWithWriteFailure() {
        storage.StorageComputeAPI storageAPI = Mockito.mock(storage.StorageComputeAPI.class);
        compute.ComputationAPI computeAPI = Mockito.mock(compute.ComputationAPI.class);
        UserComputeImpl userCompute = new UserComputeImpl(storageAPI, computeAPI);
        DataSource source = Mockito.mock(DataSource.class);
        DataDestination dest = Mockito.mock(DataDestination.class);
        Mockito.when(storageAPI.readData(source)).thenReturn(List.of(1,2,3));
        Mockito.when(computeAPI.processJob(List.of(1,2,3))).thenReturn(List.of(4,5,6));
        Mockito.when(storageAPI.writeData(List.of(4,5,6))).thenReturn(false);
        boolean result = userCompute.submitJob(source, dest, ",");
        assertFalse(result, "submitJob should return false if writeData fails");
    }

    @Test
    void testSetInputSourceReturnsSource() {
        UserComputeImpl userCompute = new UserComputeImpl(null, null);
        DataSource source = Mockito.mock(DataSource.class);
        assertSame(source, userCompute.setInputSource(source), "setInputSource should return the source");
    }

    @Test
    void testSetOutputDestinationReturnsDestination() {
        UserComputeImpl userCompute = new UserComputeImpl(null, null);
        DataDestination dest = Mockito.mock(DataDestination.class);
        assertSame(dest, userCompute.setOutputDestination(dest), "setOutputDestination should return the destination");
    }

    @Test
    void testSetDelimitersReturnsDelimiter() {
        UserComputeImpl userCompute = new UserComputeImpl(null, null);
        assertEquals(",", userCompute.setDelimiters(","), "setDelimiters should return the delimiter");
    }

    @Test
    void testGetResultsReturnsEmptyList() {
        UserComputeImpl userCompute = new UserComputeImpl(null, null);
        List<String> results = userCompute.getResults();
        assertTrue(results.isEmpty(), "getResults should return empty list");
    }

    @Test
    void testExecuteJobReturnsNull() {
        UserComputeImpl userCompute = new UserComputeImpl(null, null);
        DataSource source = Mockito.mock(DataSource.class);
        assertNull(userCompute.executeJob(source), "executeJob should return null");
    }
}