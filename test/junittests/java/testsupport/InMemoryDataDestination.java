package test.junittests.java.testsupport;

import main.java.api.user.DataDestination;
import java.util.ArrayList;
import java.util.List;

public class InMemoryDataDestination implements DataDestination {
    private final List<String> outputData = new ArrayList<>();

    public List<String> getOutputData() {
        return outputData;
    }

    public void addOutput(String value) {
        outputData.add(value);
    }

    @Override
    public String getIdentifier() {
        return "in-memory-destination";
    }
}
