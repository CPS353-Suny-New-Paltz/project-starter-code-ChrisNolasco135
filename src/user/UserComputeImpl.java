package user;

public class UserComputeImpl implements UserComputeAPI {
    // Dependencies (talking to storage)
    private storage.StorageComputeAPI storageAPI;
    private compute.ComputationAPI computeAPI;
    private String delimiter = ",";
    
    public UserComputeImpl(storage.StorageComputeAPI storageAPI, compute.ComputationAPI computeAPI) {
        this.storageAPI = storageAPI;
        this.computeAPI = computeAPI;
        
    }

    @Override
    public boolean submitJob(DataSource source, DataDestination destination, String delimiter) {
        try {
            if (source == null){
                throw new IllegalArgumentException("DataSource must not be null");
            }
            if (destination == null){
                throw new IllegalArgumentException("DataDestination must not be null");
            }
            if (delimiter == null || delimiter.isEmpty()) {
                throw new IllegalArgumentException("Delimiter must not be null or empty");
            }
            setDelimiter(delimiter);
            long inputData = storageAPI.readData(source);
            String results = computeAPI.compute(inputData);
            if (results == null){
                return false;
            }
            // Write results using the delimiter so output can be split correctly
            String output = String.join(delimiter, results.split(""));
            boolean writeSuccess = storageAPI.writeData(destination, output);
            return writeSuccess;
        } catch (Exception e) {
            System.err.println("submitJob error: " + e.getMessage());
            return false;
        }
    }
    
	public String setDelimiter(String delimiter) {
		if (delimiter == null || delimiter.trim().isEmpty()) {
			throw new IllegalArgumentException("Delimiter must not be null or empty");
		}
		this.delimiter = delimiter;
		return this.delimiter;
	}
}