package testharness;

import java.io.File;

import user.UserComputeAPI;
import user.DataSource;
import user.DataDestination;


public class TestUser {
	
	private final UserComputeAPI coordinator;

	public TestUser(UserComputeAPI coordinator) {
		this.coordinator = coordinator;
	}

	public void run(String outputPath) {
		char delimiter = ',';
		String inputPath = "test" + File.separatorChar + "testInputFile.test";

		DataSource inputSource = new DataSource() {
			@Override
			public String getIdentifier() {
				return inputPath;
			}
		};
		DataDestination outputDestination = new DataDestination() {
			@Override
			public String getIdentifier() {
				return outputPath;
			}
		};
		coordinator.submitJob(inputSource, outputDestination, String.valueOf(delimiter));
	}

}