package compute;

import java.util.Collections;
import java.util.List;

public class ComputationImpl implements ComputationAPI {
	private static final int SHIFT = 7;
    private static final int MOD = 27; // 26 letters + space

    @Override
    public List<Integer> processJob(List<Integer> inputData) {
        // For testing, just return the input data unchanged
        return inputData;
    }

	@Override
	public ComputeResult compute(ComputeRequest request) {
		int input = request.getInputData();
        String numeric = String.valueOf(input);

        // If the length is odd, pad a leading zero
        if (numeric.length() % 2 != 0) {
            numeric = "0" + numeric;
        }

        StringBuilder decoded = new StringBuilder();

        for (int i = 0; i < numeric.length(); i += 2) {
            int encodedValue = Integer.parseInt(numeric.substring(i, i + 2));
            int decodedValue = (encodedValue - SHIFT + MOD) % MOD;

            char letter;
            if (decodedValue == 26) {
                letter = ' ';
            } else {
                letter = (char) ('A' + decodedValue);
            }

            decoded.append(letter);
        }

        return new ComputeResult() {
			@Override
			public String getOutputData() {
				return decoded.toString();
			}
		};
    }
}