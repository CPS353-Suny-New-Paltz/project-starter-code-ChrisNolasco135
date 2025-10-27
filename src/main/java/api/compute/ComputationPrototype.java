package main.java.api.compute;

import project.annotations.ConceptualAPIPrototype;
import java.util.List;

public class ComputationPrototype {
	@ConceptualAPIPrototype
	public void prototype(ComputationAPI computationAPI) {

    }
	
	public ComputeResult compute(ComputeRequest request) {
		return compute(request);
	}
	
	public List<Integer> processJob(List<Integer> inputData) {
		return processJob(inputData);
	}
}
