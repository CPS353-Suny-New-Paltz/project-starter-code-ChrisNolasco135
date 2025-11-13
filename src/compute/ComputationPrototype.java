package compute;

import project.annotations.ConceptualAPIPrototype;
import java.util.List;

public class ComputationPrototype {
	@ConceptualAPIPrototype
	public void prototype(ComputationAPI computationAPI) {
		// Example ComputeRequest implementation
		ComputeRequest request = new ComputeRequest() {
			@Override
			public int getInputData() {
				return 42; // sample input
			}
		};

		// Call the compute method
		ComputeResult result = computationAPI.compute(request);

		// Process and print the output data
		String output = result.getOutputData();
		System.out.println("ComputeResult output:");
		System.out.println(output);
    }
	
	public ComputeResult compute(ComputeRequest request) {
		return compute(request);
	}
	
	public List<Integer> processJob(List<Integer> inputData) {
		return processJob(inputData);
	}
}