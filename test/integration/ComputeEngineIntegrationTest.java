package integration;

import user.UserComputeImpl;
import compute.ComputationImpl;
import testsupport.InMemoryDataSource;
import testsupport.InMemoryDataDestination;
import testsupport.InMemoryStorageComputeAPI;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ComputeEngineIntegrationTest {
    @Test
    void testComputeEngineBasicFlow() {
        // Encoded for "AB": (A=7, B=8)
        List<Integer> inputData = Arrays.asList(7, 8);
        InMemoryDataSource source = new InMemoryDataSource(inputData);
        InMemoryDataDestination destination = new InMemoryDataDestination();
        InMemoryStorageComputeAPI storageAPI = new InMemoryStorageComputeAPI(source, destination);
        ComputationImpl computationAPI = new ComputationImpl();
        UserComputeImpl userCompute = new UserComputeImpl(storageAPI, computationAPI);
        boolean result = userCompute.submitJob(source, destination, ",");
        assertTrue(result);
        // Output should be ["A", "B"]
        assertEquals(Arrays.asList("A", "B"), destination.getOutputData());
    }

    @Test
    void testComputeEngineWithSpaceAndCustomDelimiter() {
        // Encoded for "A B": (A=7, space=6, B=8)
        List<Integer> inputData = Arrays.asList(7, 6, 8);
        InMemoryDataSource source = new InMemoryDataSource(inputData);
        InMemoryDataDestination destination = new InMemoryDataDestination();
        InMemoryStorageComputeAPI storageAPI = new InMemoryStorageComputeAPI(source, destination);
        ComputationImpl computationAPI = new ComputationImpl();
        UserComputeImpl userCompute = new UserComputeImpl(storageAPI, computationAPI);
        boolean result = userCompute.submitJob(source, destination, "|");
        assertTrue(result);
        // Output should be ["A", " ", "B"]
        assertEquals(Arrays.asList("A", " ", "B"), destination.getOutputData());
    }

    @Test
    void testComputeEngineEmptyInputThrows() {
        List<Integer> inputData = Arrays.asList();
        InMemoryDataSource source = new InMemoryDataSource(inputData);
        InMemoryDataDestination destination = new InMemoryDataDestination();
        InMemoryStorageComputeAPI storageAPI = new InMemoryStorageComputeAPI(source, destination);
        ComputationImpl computationAPI = new ComputationImpl();
        UserComputeImpl userCompute = new UserComputeImpl(storageAPI, computationAPI);
        assertThrows(IllegalArgumentException.class, () -> userCompute.submitJob(source, destination, ","));
    }
}