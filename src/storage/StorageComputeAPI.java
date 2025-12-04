package storage;

import java.util.List;
import project.annotations.ProcessAPI;
import user.DataSource;

@ProcessAPI
public interface StorageComputeAPI {
	List<Integer> readData(DataSource data);         // storage → compute
    boolean writeData(List<Integer> data); // compute → storage
    void setSource(user.DataSource source);
    void setDestination(user.DataDestination destination);
    void setDelimiter(String delimiter);
}