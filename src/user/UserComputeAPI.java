package user;

import java.util.List;


import project.annotations.NetworkAPI;

@NetworkAPI
public interface UserComputeAPI {
	
	DataSource setInputSource(DataSource source);

    DataDestination setOutputDestination(DataDestination destination);

    // If user doesn’t call this, defaults will be applied
    String setDelimiters(String delimiter);

    DataSource executeJob(DataSource source);

	boolean submitJob(DataSource source, DataDestination destination, String delimiter);

	List<String> getResults();

}
