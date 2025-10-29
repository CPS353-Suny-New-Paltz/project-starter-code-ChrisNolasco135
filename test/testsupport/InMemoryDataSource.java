package testsupport;

import user.DataSource;

import java.util.List;

public class InMemoryDataSource implements DataSource {
    private final List<Integer> inputData;

    public InMemoryDataSource(List<Integer> inputData) {
        this.inputData = inputData;
    }

    public List<Integer> getInputData() {
        return inputData;
    }

    @Override
    public String getIdentifier() {
        return "in-memory-source";
    }
}
