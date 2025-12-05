package storage;

import java.util.List;
import project.annotations.ProcessAPI;
import user.DataSource;
import user.DataDestination;

@ProcessAPI
public interface StorageComputeAPI {
	List<Integer> readData(DataSource data);         // storage → compute
    boolean writeData(List<Integer> data); // compute → storage
    void setSource(DataSource source);
    void setDestination(DataDestination destination);
    void setDelimiter(String delimiter);
}