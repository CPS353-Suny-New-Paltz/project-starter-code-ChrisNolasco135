package testharness;

import java.io.File;

import user.DataSource;
import user.DataDestination;
import user.UserComputeAPI;

public class TestUser {
	
	// TODO 3: change the type of this variable to the name you're using for your
	// @NetworkAPI interface; also update the parameter passed to the constructor
	private final UserComputeAPI coordinator;

	public TestUser(user.UserComputeAPI coordinator) {
		this.coordinator = coordinator;
	}

	public void run(String outputPath) {
		char delimiter = ';';
		String inputPath = "test\testInputFile.test";
		// TODO 4: Call the appropriate method(s) on the coordinator to get it to 
		// run the compute job specified by inputPath, outputPath, and delimiter
		DataSource inputSource = () -> inputPath;
		DataDestination outputDestination = () -> outputPath;
		coordinator.submitJob(inputSource, outputDestination, String.valueOf(delimiter));
	}

}
