package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import user.DataDestination;
import user.DataSource;

public class StorageComputeImpl implements StorageComputeAPI {

	// Reads integers from the file specified by DataSource, using the provided delimiter, and returns them as a List<Integer>
	@Override
	public List<Integer> readData(DataSource source) {
		List<Integer> result = new ArrayList<>();
		try {
			if (source == null || source.getIdentifier() == null) {
				throw new IllegalArgumentException("DataSource and its identifier must not be null");
			}
			String filePath = source.getIdentifier();
			try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
				String line;
				while ((line = reader.readLine()) != null) {
					for (String part : line.split(",")) {
						try {
							result.add(Integer.parseInt(part.trim()));
						} catch (NumberFormatException e) {
							// Skip invalid integer
						}
					}
				}
			}
		} catch (IOException e) {
			System.err.println("readData error: " + e.getMessage());
		}
		return result;
	}

	// Writes a String to the file specified by DataDestination, separating each character with a delimiter, single line, 
	public boolean writeData(DataDestination destination, String data) {
		try {
			if (data == null || destination == null || destination.getIdentifier() == null) {
				throw new IllegalArgumentException("Data and destination must not be null");
			}
			String filePath = destination.getIdentifier();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < data.length(); i++) {
				sb.append(data.charAt(i));
				if (i < data.length() - 1) {
					sb.append(",");
				}
			}
			try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
				writer.write(sb.toString()); 
			}
			return true;
		} catch (IOException e) {
			throw new IllegalArgumentException("IO Exception: " + e.getMessage());
		}
	}
	
	// Writes a String to the file specified by DataDestination, separating each character with a custom delimiter, single line, no trailing newline
	public boolean writeData(DataDestination destination, String data, String delimiter) {
		try {
			if (data == null || destination == null || destination.getIdentifier() == null) {
				throw new IllegalArgumentException("Data and destination must not be null");
			}
			if (delimiter == null || delimiter.trim().isEmpty()) {
				throw new IllegalArgumentException("Delimiter must not be null or empty");
			}
			String filePath = destination.getIdentifier();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < data.length(); i++) {
				sb.append(data.charAt(i));
				if (i < data.length() - 1) {
					sb.append(delimiter);
				}
			}
			try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
				writer.write(sb.toString()); // No writer.newLine()
			}
			return true;
		} catch (IOException e) {
			throw new IllegalArgumentException("IO Exception: " + e.getMessage());
		}
	}
}