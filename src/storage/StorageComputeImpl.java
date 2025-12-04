package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import compute.ComputationAPI;
import user.DataSource;
import user.DataDestination;

public class StorageComputeImpl implements StorageComputeAPI {
    private DataSource source;
    private DataDestination destination;
    private String delimiter = ",";
	private ComputationAPI computationAPI;

    public StorageComputeImpl(compute.ComputationAPI computationAPI) {
        this.computationAPI = computationAPI;
    }

    private String getFilePath(DataSource data) {
        return data != null ? data.getIdentifier() : null;
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
                   try {
                   		result.add(Integer.parseInt(part.trim()));
                   }catch (NumberFormatException e) {
                   		// Skip invalid integer
                   }
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
            // Write all integers as a single comma-separated line
            String line = data.stream()
                .map(Object::toString)
                .reduce((a, b) -> a + delimiter + b)
                .orElse("");
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    @Override
    public void setSource(user.DataSource source) {
        this.source = source;
    }

    @Override
    public void setDestination(user.DataDestination destination) {
        this.destination = destination;
    }

    @Override
    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }
}