package compute;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestComputationAPI {
    @Test
    void testComputeDecodesSingleLetter() {
        ComputationImpl impl = new ComputationImpl();
        // 'A' encoded: (0 + 7) % 27 = 7, so input is 07
        String res = impl.compute(07);
        assertEquals("A", res, "compute should decode single letter 'A'");
    }

    @Test
    void testComputeDecodesSpace() {
        ComputationImpl impl = new ComputationImpl();
        // Space encoded: (26 + 7) % 27 = 6, so input is 33
        // But decoding: (33 - 7 + 27) % 27 = 26 -> space
        String res = impl.compute(33);
        assertEquals(" ", res, "compute should decode space");
    }

    @Test
    void testComputeDecodesMultipleLetters() {
        ComputationImpl impl = new ComputationImpl();
        // "AB" encoded: 07 08, cannot begin with 0 in long, so input is 708
        String res = impl.compute(708);
        assertEquals("AB", res, "compute should decode 'AB'");
    }

    @Test
    void testComputePadsOddLengthInput() {
        ComputationImpl impl = new ComputationImpl();
        // Odd length input: 7 -> pads to 07, decodes to 'A'
        String res = impl.compute(7);
        assertEquals("A", res, "compute should pad odd length input");
    }

    @Test
    void testComputeWithZeroInput() {
        ComputationImpl impl = new ComputationImpl();
        // 00: (0 - 7 + 27) % 27 = 20 -> 'U'
        String res = impl.compute(0);
        assertEquals("U", res, "compute should decode zero input");
    }

    @Test
    void testComputeWithEmptyInput() {
        ComputationImpl impl = new ComputationImpl();
        // Empty input: 0 -> pads to 00
        String res = impl.compute(0);
        assertEquals("U", res, "compute should decode padded zero as 'U'");
    }

    @Test
    void testComputeThrowsOnNullRequest() {
        ComputationImpl impl = new ComputationImpl();
        assertThrows(IllegalArgumentException.class, () -> impl.compute(Long.parseLong("")), "compute should throw IllegalArgumentException for empty input");
    }
}