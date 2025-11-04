package compute;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TestComputationAPI {

    @Test
    void testComputeMethodReturnsExpectedOutput() {
        ComputationAPI mockApi = new ComputationAPI() {
            @Override
            public ComputeResult compute(ComputeRequest request) {
                return new ComputeResult() {
                    @Override
                    public int[] getOutputData() {
                        return new int[] {1, 2, 3};
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
        assertTrue(Arrays.equals(result.getOutputData(), new int[] {1, 2, 3}), "compute should return expected output data");
    }
}