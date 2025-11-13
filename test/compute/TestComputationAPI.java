package compute;

import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestComputationAPI {

    @Test
    void testComputeMethodReturnsExpectedOutput() {
        ComputationAPI mockApi = new ComputationAPI() {
            @Override
            public ComputeResult compute(ComputeRequest request) {
                return new ComputeResult() {
                    @Override
                    public String getOutputData() {
                        return new String("TEST");
                    }
                };
            }
            @Override
            public List<Integer> processJob(List<Integer> inputData) {
                return Arrays.asList(1, 2, 3);
            }
        };
        ComputeRequest mockRequest = new ComputeRequest() {
            @Override
            public int getInputData() {
                return 1;
            }
        };
        ComputeResult result = mockApi.compute(mockRequest);
        assertEquals("TEST", result.getOutputData(), "compute should return expected output data");
    }
}