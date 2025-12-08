package integration;

import user.UserComputeImpl;
import compute.ComputationImpl;
import testsupport.InMemoryDataSource;
import testsupport.InMemoryDataDestination;
import testsupport.InMemoryStorageComputeAPI;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;


class ComputeEngineIntegrationTest {
    @Test
    void testEndToEndWithInMemoryDataStore() {
        // Input: [1, 10, 25]
        InMemoryDataSource source = new InMemoryDataSource(List.of(1, 10, 25));
        InMemoryDataDestination destination = new InMemoryDataDestination();
        InMemoryStorageComputeAPI storageAPI = new InMemoryStorageComputeAPI(source, destination);
        ComputationImpl computeAPI = new ComputationImpl();
        UserComputeImpl engine = new UserComputeImpl(storageAPI, computeAPI);
        boolean success = engine.submitJob(source, destination, ",");
        assertTrue(success);
        List<String> output = destination.getOutputData();
        assertEquals(List.of("B", "K", "Z"), output); // 1->B, 10->K, 25->Z (shift 7 Caesar logic)
    }

    @Test
    void testEndToEndWithEmptyInput() {
        InMemoryDataSource source = new InMemoryDataSource(List.of());
        InMemoryDataDestination destination = new InMemoryDataDestination();
        InMemoryStorageComputeAPI storageAPI = new InMemoryStorageComputeAPI(source, destination);
        ComputationImpl computeAPI = new ComputationImpl();
        UserComputeImpl engine = new UserComputeImpl(storageAPI, computeAPI);
        boolean success = engine.submitJob(source, destination, ",");
        assertFalse(success);
        assertTrue(destination.getOutputData().isEmpty());
    }

    @Test
    void testEndToEndWithNullInput() {
        InMemoryDataDestination destination = new InMemoryDataDestination();
        InMemoryStorageComputeAPI storageAPI = new InMemoryStorageComputeAPI(null, destination);
        ComputationImpl computeAPI = new ComputationImpl();
        UserComputeImpl engine = new UserComputeImpl(storageAPI, computeAPI);
        boolean success = engine.submitJob(null, destination, ",");
        assertFalse(success);
        assertTrue(destination.getOutputData().isEmpty());
    }

    @Test
    void testComputationExceptionHandling() {
        InMemoryDataSource source = new InMemoryDataSource(List.of(1, 10, 25));
        InMemoryDataDestination destination = new InMemoryDataDestination();
        InMemoryStorageComputeAPI storageAPI = new InMemoryStorageComputeAPI(source, destination);
        // Custom computeAPI that throws exception
        compute.ComputationAPI computeAPI = input -> { throw new RuntimeException("Computation failed"); };
        UserComputeImpl engine = new UserComputeImpl(storageAPI, computeAPI);
        boolean success = engine.submitJob(source, destination, ",");
        assertFalse(success);
        assertTrue(destination.getOutputData().isEmpty());
    }
}