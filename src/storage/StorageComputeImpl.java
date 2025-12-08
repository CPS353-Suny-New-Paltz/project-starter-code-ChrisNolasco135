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

	// Reads integers from the file specified by DataSource, using comma as delimiter, and returns them as a single long by concatenation
	public long readData(DataSource source) {
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
			if (result.isEmpty()) return 0L;
			StringBuilder sb = new StringBuilder();
			for (Integer num : result) {
				sb.append(num);
			}
			try {
				return Long.parseLong(sb.toString());
			} catch (NumberFormatException e) {
				System.err.println("readData conversion error: " + e.getMessage());
				return 0L;
			}
		} catch (IOException e) {
			System.err.println("readData error: " + e.getMessage());
			return 0L;
		}
	}

	// Writes a String to the file specified by DataDestination
	public boolean writeData(DataDestination destination, String data) {
		try {
			if (data == null || destination == null || destination.getIdentifier() == null) {
				throw new IllegalArgumentException("Data and destination must not be null");
			}
			String filePath = destination.getIdentifier();
			try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
				writer.write(data);
				writer.newLine();
			}
			return true;
		} catch (IOException e) {
			System.err.println("writeData error: " + e.getMessage());
			return false;
		}
	}
	
	// Writes a String to the file specified by DataDestination, using a custom delimiter
	public boolean writeData(DataDestination destination, String data, String delimiter) {
		try {
			if (data == null || destination == null || destination.getIdentifier() == null) {
				throw new IllegalArgumentException("Data and destination must not be null");
			}
			if (delimiter == null || delimiter.trim().isEmpty()) {
				throw new IllegalArgumentException("Delimiter must not be null or empty");
			}
			String filePath = destination.getIdentifier();
			String output = data.replace(",", delimiter);
			try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
				writer.write(output);
				writer.newLine();
			}
			return true;
		} catch (IOException e) {
			System.err.println("writeData error: " + e.getMessage());
			return false;
		}
	}

}