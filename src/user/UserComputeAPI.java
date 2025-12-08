package user;

import project.annotations.NetworkAPI;

@NetworkAPI
public interface UserComputeAPI {
	boolean submitJob(DataSource source, DataDestination destination, String delimiter);
	String setDelimiter(String delimiter);
}
