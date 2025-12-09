package project.checkpointtests;

import compute.ComputationImpl;
import storage.StorageComputeImpl;
import user.UserComputeImpl;
import user.DataSource;
import user.DataDestination;

public class ManualTestingFramework {
    public static final String INPUT = "manualTestInput.txt";
    public static final String OUTPUT = "manualTestOutput.txt";

    public static void main(String[] args) {
        // TODO 1:
        // Instantiate a real (ie, class definition lives in the src/ folder) implementation 
        // of all 3 APIs
        ComputationImpl computationAPI = new ComputationImpl();
        StorageComputeImpl storageAPI = new StorageComputeImpl();
        UserComputeImpl userAPI = new UserComputeImpl(storageAPI, computationAPI);

        // TODO 2:
        // Run a computation with an input file of <root project dir>/manualTestInput.txt
        // and an output of <root project dir>/manualTestOutput.txt, with a delimiter of ',' 
        DataSource inputSource = new DataSource() {
			@Override
			public String getIdentifier() {
				return INPUT;
			}
		};
        DataDestination outputDestination = new DataDestination() {
        	@Override
        	public String getIdentifier() {
        		return OUTPUT;
        	}
        };
        String delimiter = ",";

        boolean success = userAPI.submitJob(inputSource, outputDestination, delimiter);
        System.out.println("Computation success: " + success);
    }
}