package main.java.api.compute;

import project.annotations.ConceptualAPI;

@ConceptualAPI
public interface ComputationAPI {
	ComputeResult compute(ComputeRequest request);
}
