package main.java.api.compute;

import main.java.project.annotations.ConceptualAPI;

@ConceptualAPI
public interface ComputationAPI {
	ComputeResult compute(ComputeRequest request);
}
