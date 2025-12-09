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
        try {
            ComputationImpl computationAPI = new ComputationImpl();
            StorageComputeImpl storageAPI = new StorageComputeImpl();
            UserComputeImpl userAPI = new UserComputeImpl(storageAPI, computationAPI);

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
        } catch (Exception e) {
            System.err.println("Error during computation: " + e.getMessage());
            // Do not propagate exception
        }
    }
}