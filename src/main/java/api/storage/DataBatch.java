package main.java.api.storage;

public interface DataBatch {
	int[] getData(); // or List<Integer>, but array keeps it simple

	Object isEmpty();
}
