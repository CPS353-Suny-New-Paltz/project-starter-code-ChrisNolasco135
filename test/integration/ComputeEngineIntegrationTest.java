package integration;

import user.UserComputeImpl;

import testsupport.InMemoryDataSource;
import testsupport.InMemoryDataDestination;
import testsupport.InMemoryStorageComputeAPI;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Tag;

@Tag("integration")

class ComputeEngineIntegrationTest {

    @Test
    void testEndToEndWithInMemoryDataStore() {
        // Input: integers [1, 10, 25]
        InMemoryDataSource input = new InMemoryDataSource(List.of(1, 10, 25));
        InMemoryDataDestination output = new InMemoryDataDestination();

        // Wire up storage and compute components
        InMemoryStorageComputeAPI storage = new InMemoryStorageComputeAPI(input, output);
        UserComputeImpl userCompute = new UserComputeImpl(storage);

        // Run the job (no delimiter specified, so should use defaults)
        boolean success = userCompute.submitJob(input, output, null);

        // Validate (will FAIL until you implement the compute engine)
        assertTrue(success, "Job should succeed (currently stubbed to false)");

        // Expected output: some transformation of [1, 10, 25]
        // Since computation isn’t implemented yet, this will fail intentionally.
        List<String> results = output.getOutputData();
        assertFalse(results.isEmpty(), "Eventually, results should not be empty");
        assertEquals(List.of("1", "10", "25"), results,
            "Eventually, compute engine should output stringified integers");
    }
}

