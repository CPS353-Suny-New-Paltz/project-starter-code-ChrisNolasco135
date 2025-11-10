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
			// Set input source
			userComputeAPI.setInputSource(inputSource);
			// Set output destination
			userComputeAPI.setOutputDestination(outputDestination);
			// Set delimiters
			userComputeAPI.setDelimiters(",");
			// Submit job
			boolean submitted = userComputeAPI.submitJob(inputSource, outputDestination, ",");
			System.out.println("Job submitted: " + submitted);
			// Execute job
			DataSource resultSource = userComputeAPI.executeJob(inputSource);
			System.out.println("Executed job, result source: " + resultSource.getIdentifier());
			// Get results
			java.util.List<String> results = userComputeAPI.getResults();
			System.out.println("Results:");
			for (String result : results) {
				System.out.println(result);
			}
        }
	
		public DataSource setInputSource(DataSource source) {
			return source;
		}
		
		public DataDestination setOutputDestination(DataDestination destination) {
			return destination;
		}
		
		public String setDelimiters(String delimiter) {	
			return delimiter;
		}
		
		public boolean submitJob(DataSource source, DataDestination destination, String delimiter) {
			return false;
		}
		
		public DataSource executeJob(DataSource source) {
			return source;
		}
    }