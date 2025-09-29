package project.annotations;

public class ComputationPrototype {
	
	@ConceptualAPIPrototype
	public static ComputationAPI createPrototype() {
        return new ComputationAPI() {
            @Override
            public ComputeResult compute(ComputeRequest request) {
                int[] input = request.getInputData();
                int[] output = new int[input.length];

                // Example computation: square each number
                for (int i = 0; i < input.length; i++) {
                    output[i] = input[i] * input[i];
                }

                return () -> output; // return lambda as ComputeResult
            }
        };
    }
}
