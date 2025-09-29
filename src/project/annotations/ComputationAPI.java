package project.annotations;

@ConceptualAPI
public interface ComputationAPI {
	ComputeResult compute(ComputeRequest request);
}
