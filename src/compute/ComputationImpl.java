package compute;

public class ComputationImpl implements ComputationAPI {
	private static final int SHIFT = 7;
    private static final int MOD = 27; // 26 letters + space

    @Override
    public String compute(long input) {
        try {
            String numeric = String.valueOf(input);
            if (numeric == null || numeric.isEmpty()) {
                throw new IllegalArgumentException("Input data must not be empty");
            }
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
                return decoded.toString();
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            System.err.println("compute error: " + e.getMessage());
            throw new RuntimeException("Computation failed", e);
        }
    }
}