package project.annotations;

@ProcessAPI
public interface StorageComputeAPI {
	DataBatch readData();         // storage → compute
    void writeData(DataBatch data); // compute → storage
}
