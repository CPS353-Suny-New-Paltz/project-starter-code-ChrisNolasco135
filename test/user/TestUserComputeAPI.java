package user;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class TestUserComputeAPI {
    @Test
    void testSubmitJobWithValidInput() {
        user.DataSource source = Mockito.mock(user.DataSource.class);
        Mockito.when(source.getIdentifier()).thenReturn("input.txt");
        user.DataDestination destination = Mockito.mock(user.DataDestination.class);
        Mockito.when(destination.getIdentifier()).thenReturn("output.txt");
        storage.StorageComputeAPI storageAPI = Mockito.mock(storage.StorageComputeAPI.class);
        compute.ComputationAPI computeAPI = Mockito.mock(compute.ComputationAPI.class);
        Mockito.when(storageAPI.readData(source)).thenReturn(123L);
        Mockito.when(computeAPI.compute(123L)).thenReturn("ABC");
        Mockito.when(storageAPI.writeData(destination, "A,B,C")).thenReturn(true);
        user.UserComputeImpl userCompute = new user.UserComputeImpl(storageAPI, computeAPI);
        boolean result = userCompute.submitJob(source, destination, ",");
        assertTrue(result);
    }

    @Test
    void testSubmitJobWithEmptyInput() {
        user.DataSource source = Mockito.mock(user.DataSource.class);
        Mockito.when(source.getIdentifier()).thenReturn("input.txt");
        user.DataDestination destination = Mockito.mock(user.DataDestination.class);
        Mockito.when(destination.getIdentifier()).thenReturn("output.txt");
        storage.StorageComputeAPI storageAPI = Mockito.mock(storage.StorageComputeAPI.class);
        compute.ComputationAPI computeAPI = Mockito.mock(compute.ComputationAPI.class);
        Mockito.when(storageAPI.readData(source)).thenReturn(0L);
        Mockito.when(computeAPI.compute(0L)).thenReturn("");
        Mockito.when(storageAPI.writeData(destination, "")).thenReturn(false);
        user.UserComputeImpl userCompute = new user.UserComputeImpl(storageAPI, computeAPI);
        boolean result = userCompute.submitJob(source, destination, ",");
        assertFalse(result);
    }

    @Test
    void testSubmitJobWithNullArguments() {
        storage.StorageComputeAPI storageAPI = Mockito.mock(storage.StorageComputeAPI.class);
        compute.ComputationAPI computeAPI = Mockito.mock(compute.ComputationAPI.class);
        user.UserComputeImpl userCompute = new user.UserComputeImpl(storageAPI, computeAPI);
        assertThrows(IllegalArgumentException.class, () -> userCompute.submitJob(null, Mockito.mock(user.DataDestination.class), ","));
        assertThrows(IllegalArgumentException.class, () -> userCompute.submitJob(Mockito.mock(user.DataSource.class), null, ","));
        assertThrows(IllegalArgumentException.class, () -> userCompute.submitJob(Mockito.mock(user.DataSource.class), Mockito.mock(user.DataDestination.class), null));
        assertThrows(IllegalArgumentException.class, () -> userCompute.submitJob(Mockito.mock(user.DataSource.class), Mockito.mock(user.DataDestination.class), ""));
    }

    @Test
    void testSetDelimiterValid() {
        storage.StorageComputeAPI storageAPI = Mockito.mock(storage.StorageComputeAPI.class);
        compute.ComputationAPI computeAPI = Mockito.mock(compute.ComputationAPI.class);
        user.UserComputeImpl userCompute = new user.UserComputeImpl(storageAPI, computeAPI);
        assertEquals("|", userCompute.setDelimiter("|"));
        assertEquals(",", userCompute.setDelimiter(","));
    }

    @Test
    void testSetDelimiterInvalid() {
        storage.StorageComputeAPI storageAPI = Mockito.mock(storage.StorageComputeAPI.class);
        compute.ComputationAPI computeAPI = Mockito.mock(compute.ComputationAPI.class);
        user.UserComputeImpl userCompute = new user.UserComputeImpl(storageAPI, computeAPI);
        assertThrows(IllegalArgumentException.class, () -> userCompute.setDelimiter(""));
        assertThrows(IllegalArgumentException.class, () -> userCompute.setDelimiter(null));
    }
}