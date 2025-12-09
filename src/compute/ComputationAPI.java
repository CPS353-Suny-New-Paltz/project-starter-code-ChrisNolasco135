package compute;

import java.util.List;

import project.annotations.ConceptualAPI;

@ConceptualAPI
public interface ComputationAPI {
	String compute(List<Integer> input);
	
}
