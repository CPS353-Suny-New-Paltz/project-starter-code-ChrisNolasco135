package com.project.api.compute;

import project.annotations.ConceptualAPI;

@ConceptualAPI
public interface ComputationAPI {
	ComputeResult compute(ComputeRequest request);
}
