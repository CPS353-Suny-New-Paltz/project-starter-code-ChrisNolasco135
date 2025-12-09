package compute;

import project.annotations.ConceptualAPIPrototype;

import java.util.Arrays;
import java.util.List;

public class ComputationPrototype {
	@ConceptualAPIPrototype
	public void prototype(ComputationAPI computationAPI) {
		// Example ComputeRequest implementation
		
		List<Integer> inputData = Arrays.asList(1,2,3);
		String result = computationAPI.compute(inputData);
		// Process and print the output data
		System.out.println("ComputeResult output:");
		System.out.println(result);
    }
	
}