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
import compute.ComputationAPI;

public class StorageComputeImpl implements StorageComputeAPI {
    private DataSource source;
    private DataDestination destination;
    private String delimiter = ",";
    private ComputationAPI computationAPI;

    public StorageComputeImpl(compute.ComputationAPI computationAPI) {
        this.computationAPI = computationAPI;
    }

    private String getFilePath(DataSource data) {
        if (data == null) {
            return null;
        }
        return data.getIdentifier();
    }

    @Override
    public List<Integer> readData(DataSource data) {
        try {
            if (data == null || data.getIdentifier() == null) {
                throw new IllegalArgumentException("DataSource and its identifier must not be null");
            }
            String filePath = getFilePath(data);
            List<Integer> result = new ArrayList<>();
            if (filePath == null) {
                return result;
            }
            try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    for (String part : line.split("[ ,;\t]+")) {
                        try {
                            result.add(Integer.parseInt(part.trim()));
                        } catch (NumberFormatException e) {
                            // Skip invalid integer
                        }
                    }
                }
            }
            return result;
        } catch (Exception e) {
            System.err.println("readData error: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public boolean writeData(List<Integer> data) {
        try {
            if (data == null) {
                throw new IllegalArgumentException("Data list must not be null");
            }
            if (destination == null) {
                return false;
            }
            String filePath = destination.getIdentifier();
            if (filePath == null) {
                return false;
            }
            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
                // Write all integers as a single comma-separated line
                String line = data.stream()
                    .map(Object::toString)
                    .reduce((a, b) -> a + delimiter + b)
                    .orElse("");
                writer.write(line);
                writer.newLine();
            }
            return true;
        } catch (Exception e) {
            System.err.println("writeData error: " + e.getMessage());
            return false;
        }
    }

    // Optionally add methods to set source/destination
    public void setSource(DataSource source) {
        if (source == null) {
            throw new IllegalArgumentException("Source must not be null");
        }
        this.source = source;
    }
    public void setDestination(DataDestination destination) {
        if (destination == null) {
            throw new IllegalArgumentException("Destination must not be null");
        }
        this.destination = destination;
    }
    public void setDelimiter(String delimiter) {
        if (delimiter == null || delimiter.isEmpty()) {
            throw new IllegalArgumentException("Delimiter must not be null or empty");
        }
        this.delimiter = delimiter;
    }
}