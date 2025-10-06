package test.junittests.java.compute;

import org.junit.jupiter.api.Test;


import main.java.api.compute.ComputationImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TestComputationAPI {

    @Test
    void testProcessJobReturnsEmptyList() {
        ComputationImpl computation = new ComputationImpl();

        List<Integer> result = computation.processJob(List.of(1, 2, 3));
        assertTrue(result.isEmpty(), "Default processJob should return empty list");
    }
}
