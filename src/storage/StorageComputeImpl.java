package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import user.DataSource;
import user.DataDestination;

public class StorageComputeImpl implements StorageComputeAPI {
    private DataSource source;
    private DataDestination destination;
    private String delimiter = ",";

    public StorageComputeImpl(compute.ComputationAPI computationAPI) {
        // Optionally store computationAPI if needed
    }

    private String getFilePath(DataSource data) {
        if (data instanceof user.DataSource) {
            return ((user.DataSource) data).getIdentifier();
        }
        return null;
    }

    @Override
    public List<Integer> readData(DataSource data) {
        String filePath = getFilePath(data);
        List<Integer> result = new ArrayList<>();
        if (filePath == null) {
        	return result;
        }
        try(BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                for (String part : line.split("[ ,;\t]+")) {
                   result.add(Integer.parseInt(part.trim()));
                }
            }
        }catch (IOException | NullPointerException e) {
            	return new ArrayList<>();
            }
            return result;
        }
        
    @Override
    public boolean writeData(List<Integer> data) {
        if (destination == null) {
        	return false;
        }
        String filePath = destination.getIdentifier();
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
            for (Integer i : data) {
                writer.write(i.toString());
                writer.write(" ");
            }
            writer.newLine();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    // Optionally add methods to set source/destination
    public void setSource(DataSource source) {
        this.source = source;
    }
    public void setDestination(DataDestination destination) {
        this.destination = destination;
    }
    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }
}