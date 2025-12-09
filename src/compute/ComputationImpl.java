package compute;

import java.util.List;

public class ComputationImpl implements ComputationAPI {
    private static final int SHIFT = 7;
    private static final int MOD = 27; // 26 letters + space

    @Override
    public String compute(List<Integer> input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Input data must not be empty");
        }
        StringBuilder decoded = new StringBuilder();
        for (Integer encodedValue : input) {
            if (encodedValue == null) {
                continue; // skip nulls
            }
            int decodedValue = (encodedValue - SHIFT + MOD) % MOD;
            char letter;
            if (decodedValue == 26) {
                letter = ' ';
            } else {
                letter = (char) ('A' + decodedValue);
            }
            decoded.append(letter);
        }
        return decoded.toString();
    }
}