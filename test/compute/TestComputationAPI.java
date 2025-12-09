package compute;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestComputationAPI {
    @Test
    void testComputeDecodesSingleLetter() {
        ComputationImpl impl = new ComputationImpl();
        // 'A' encoded: (0 + 7) % 27 = 7
        List<Integer> input = Arrays.asList(7);
        String res = impl.compute(input);
        assertEquals("A", res, "compute should decode single letter 'A'");
    }

    @Test
    void testComputeDecodesSpace() {
        ComputationImpl impl = new ComputationImpl();
        // Space encoded: (26 + 7) % 27 = 6
        List<Integer> input = Arrays.asList(6);
        String res = impl.compute(input);
        assertEquals(" ", res, "compute should decode space");
    }

    @Test
    void testComputeDecodesMultipleLetters() {
        ComputationImpl impl = new ComputationImpl();
        // "AB" encoded: 7, 8
        List<Integer> input = Arrays.asList(7, 8);
        String res = impl.compute(input);
        assertEquals("AB", res, "compute should decode 'AB'");
    }

    @Test
    void testComputeWithZeroInput() {
        ComputationImpl impl = new ComputationImpl();
        // 0: (0 - 7 + 27) % 27 = 20 -> 'U'
        List<Integer> input = Arrays.asList(0);
        String res = impl.compute(input);
        assertEquals("U", res, "compute should decode zero input");
    }

    @Test
    void testComputeWithEmptyInput() {
        ComputationImpl impl = new ComputationImpl();
        // Empty input: should throw exception
        assertThrows(IllegalArgumentException.class, () -> impl.compute(Arrays.asList()), "compute should throw IllegalArgumentException for empty input");
    }

    @Test
    void testComputeThrowsOnNullRequest() {
        ComputationImpl impl = new ComputationImpl();
        assertThrows(IllegalArgumentException.class, () -> impl.compute(null), "compute should throw IllegalArgumentException for null input");
    }
}