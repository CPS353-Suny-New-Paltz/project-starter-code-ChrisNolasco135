package user;

import project.annotations.NetworkAPIPrototype;

public class UserComputePrototype {
	@NetworkAPIPrototype
		public void prototype(UserComputeAPI userComputeAPI) {
			// Example DataSource implementation
			DataSource inputSource = new DataSource() {
				@Override
				public String getIdentifier() {
					return "input-file.txt";
				}
			};
			// Example DataDestination implementation
			DataDestination outputDestination = new DataDestination() {
				@Override
				public String getIdentifier() {
					return "output-file.txt";
				}
			};
			// Submit job
			boolean submitted = userComputeAPI.submitJob(inputSource, outputDestination, ",");
			System.out.println("Job submitted: " + submitted);
		}
	}