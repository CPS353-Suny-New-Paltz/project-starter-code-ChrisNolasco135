package compute;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestComputationAPI {
    @Test
    void testProcessJobReturnsInputUnchanged() {
        ComputationImpl impl = new ComputationImpl();
        List<Integer> input = Arrays.asList(1, 2, 3);
        List<Integer> result = impl.processJob(input);
        assertEquals(input, result, "processJob should return input unchanged");
    }

    @Test
    void testProcessJobWithEmptyList() {
        ComputationImpl impl = new ComputationImpl();
        List<Integer> input = Collections.emptyList();
        List<Integer> result = impl.processJob(input);
        assertEquals(input, result, "processJob should handle empty list");
    }

    @Test
    void testComputeDecodesSingleLetter() {
        ComputationImpl impl = new ComputationImpl();
        // 'A' encoded: (0 + 7) % 27 = 7, so input is 07
        ComputeRequest req = () -> 7;
        ComputeResult res = impl.compute(req);
        assertEquals("A", res.getOutputData(), "compute should decode single letter 'A'");
    }

    @Test
    void testComputeDecodesSpace() {
        ComputationImpl impl = new ComputationImpl();
        // Space encoded: (26 + 7) % 27 = 6, so input is 33
        // But decoding: (33 - 7 + 27) % 27 = 26 -> space
        ComputeRequest req = () -> 33;
        ComputeResult res = impl.compute(req);
        assertEquals(" ", res.getOutputData(), "compute should decode space");
    }

    @Test
    void testComputeDecodesMultipleLetters() {
        ComputationImpl impl = new ComputationImpl();
        // "AB" encoded: 07 08
        ComputeRequest req = () -> 708;
        ComputeResult res = impl.compute(req);
        assertEquals("AB", res.getOutputData(), "compute should decode 'AB'");
    }

    @Test
    void testComputePadsOddLengthInput() {
        ComputationImpl impl = new ComputationImpl();
        // Odd length input: 7 -> pads to 07, decodes to 'A'
        ComputeRequest req = () -> 7;
        ComputeResult res = impl.compute(req);
        assertEquals("A", res.getOutputData(), "compute should pad odd length input");
    }

    @Test
    void testComputeWithZeroInput() {
        ComputationImpl impl = new ComputationImpl();
        // 00: (0 - 7 + 27) % 27 = 20 -> 'U'
        ComputeRequest req = () -> 0;
        ComputeResult res = impl.compute(req);
        assertEquals("U", res.getOutputData(), "compute should decode zero input");
    }

    @Test
    void testComputeWithEmptyInput() {
        ComputationImpl impl = new ComputationImpl();
        // Empty input: 0 -> pads to 00
        ComputeRequest req = () -> 0;
        ComputeResult res = impl.compute(req);
        assertEquals("U", res.getOutputData(), "compute should decode padded zero as 'U'");
    }
}