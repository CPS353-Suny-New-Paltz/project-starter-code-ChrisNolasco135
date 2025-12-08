package testsupport;


import storage.StorageComputeAPI;
import user.DataDestination;
import user.DataSource;

import java.util.List;

public class InMemoryStorageComputeAPI implements StorageComputeAPI {
    private final InMemoryDataSource source;
    private final InMemoryDataDestination destination;
    private String delimiter = ",";

    public InMemoryStorageComputeAPI(InMemoryDataSource source, InMemoryDataDestination destination) {
        this.source = source;
        this.destination = destination;
    }

    @Override
    public long readData(DataSource data) {
        if (source == null || source.getInputData() == null || source.getInputData().isEmpty()) return 0L;
        List<Integer> input = source.getInputData();
        StringBuilder sb = new StringBuilder();
        for (Integer num : input) {
            sb.append(num);
        }
        try {
            return Long.parseLong(sb.toString());
        } catch (NumberFormatException e) {
            return 0L;
        }
    }

    @Override
    public boolean writeData(DataDestination destination, String data) {
        if (data == null || destination == null) return false;
        if (!(destination instanceof testsupport.InMemoryDataDestination)) return false;
        testsupport.InMemoryDataDestination memDest = (testsupport.InMemoryDataDestination) destination;
        memDest.getOutputData().clear();
        for (String part : data.split(delimiter)) {
            memDest.addOutput(part);
        }
        return true;
    }
}