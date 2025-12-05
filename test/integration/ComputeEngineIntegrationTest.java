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


public class ComputeEngineIntegrationTest {

    @Test
    public void testEndToEndWithInMemoryDataStore() {
        // Input: integers [1, 10, 25]
        InMemoryDataSource input = new InMemoryDataSource(List.of(1, 10, 25));
        InMemoryDataDestination output = new InMemoryDataDestination();

        // Wire up storage and compute components
        ComputationImpl computation = new ComputationImpl();
        InMemoryStorageComputeAPI storage = new InMemoryStorageComputeAPI(input, output);
        UserComputeImpl userCompute = new UserComputeImpl(storage, computation);

        // Run the job (pass a valid delimiter)
        boolean success = userCompute.submitJob(input, output, ",");

        // Validate (will FAIL until you implement the compute engine)
        assertTrue(success, "Job should succeed (currently stubbed to false)");

        // Expected output: some transformation of [1, 10, 25]
        List<String> results = output.getOutputData();
        assertFalse(results.isEmpty(), "Eventually, results should not be empty");
        assertEquals(List.of("1", "10", "25"), results,
            "Eventually, compute engine should output stringified integers");
    }

    @Test
    public void testEndToEndWithEmptyInput() {
        // Input: empty list
    	// Additional test to verify behavior with empty input
        InMemoryDataSource input = new InMemoryDataSource(List.of());
        InMemoryDataDestination output = new InMemoryDataDestination();

        ComputationImpl computation = new ComputationImpl();
        InMemoryStorageComputeAPI storage = new InMemoryStorageComputeAPI(input, output);
        UserComputeImpl userCompute = new UserComputeImpl(storage, computation);

        boolean success = userCompute.submitJob(input, output, null);

        assertFalse(success, "Job should fail with empty input");
        List<String> results = output.getOutputData();
        assertTrue(results.isEmpty(), "Output should be empty for empty input");
    }

    @Test
    public void testExceptionHandlingWithNullDataSource() {
        // Output destination is valid, but input source is null
        InMemoryDataDestination output = new InMemoryDataDestination();
        ComputationImpl computation = new ComputationImpl();
        InMemoryStorageComputeAPI storage = new InMemoryStorageComputeAPI(null, output);
        UserComputeImpl userCompute = new UserComputeImpl(storage, computation);
        boolean success = false;
        try {
            success = userCompute.submitJob(null, output, ",");
        } catch (Exception e) {
            // If exception is thrown, the test should fail
            assertFalse(true, "Exception should not propagate to process boundary: " + e.getMessage());
        }
        assertFalse(success, "submitJob should return false and handle exception internally for null DataSource");
        List<String> results = output.getOutputData();
        assertTrue(results.isEmpty(), "Output should be empty when exception is handled");
    }

    @Test
    public void testExceptionInComputationIsCaughtAndTransformed() {
        // Stub ComputationAPI that throws exception
        compute.ComputationAPI throwingComputation = new compute.ComputationAPI() {
            @Override
            public java.util.List<Integer> processJob(java.util.List<Integer> inputData) {
                throw new RuntimeException("Simulated computation failure");
            }
            @Override
            public compute.ComputeResult compute(compute.ComputeRequest request) {
                throw new RuntimeException("Simulated computation failure");
            }
        };
        InMemoryDataSource input = new InMemoryDataSource(List.of(1, 2, 3));
        InMemoryDataDestination output = new InMemoryDataDestination();
        InMemoryStorageComputeAPI storage = new InMemoryStorageComputeAPI(input, output);
        UserComputeImpl userCompute = new UserComputeImpl(storage, throwingComputation);
        boolean success = true;
        try {
            success = userCompute.submitJob(input, output, ",");
        } catch (Exception e) {
            assertFalse(true, "Exception should not propagate to process boundary: " + e.getMessage());
        }
        assertFalse(success, "submitJob should return false and handle computation exception internally");
        List<String> results = output.getOutputData();
        assertTrue(results.isEmpty(), "Output should be empty when computation exception is handled");
    }
}