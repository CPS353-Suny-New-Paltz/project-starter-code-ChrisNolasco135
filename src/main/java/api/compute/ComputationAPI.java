package main.java.api.compute;

import java.util.List;

import project.annotations.ConceptualAPI;

@ConceptualAPI
public interface ComputationAPI {
	ComputeResult compute(ComputeRequest request);

	List<Integer> processJob(List<Integer> inputData);
}
