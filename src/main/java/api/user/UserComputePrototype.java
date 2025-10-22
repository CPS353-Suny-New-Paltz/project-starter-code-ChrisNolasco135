package main.java.api.user;

import project.annotations.NetworkAPIPrototype;

public class UserComputePrototype {
	@NetworkAPIPrototype
		public void prototype(UserComputeAPI userComputeAPI) {
			
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
