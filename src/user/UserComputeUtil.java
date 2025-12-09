package user;

import java.util.List;

public class UserComputeUtil {
    public static boolean processJob(
            storage.StorageComputeAPI storageAPI,
            compute.ComputationAPI computeAPI,
            DataSource source,
            DataDestination destination,
            String delimiter) {
        if (source == null || destination == null || delimiter == null) {
            throw new IllegalArgumentException("DataSource, DataDestination or Delimiter must not be null");
        }
        // Read input as List<Integer> from DataSource using the provided delimiter
        List<Integer> inputData = storageAPI.readData(source);
        // Compute the result using the full list
        String result = computeAPI.compute(inputData);
        // Write the result as a single line, separated by the delimiter
        return storageAPI.writeData(destination, result, delimiter);
    }
}