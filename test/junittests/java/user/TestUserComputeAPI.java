package test.junittests.java.user;

import storage.StorageComputeAPI;


import user.DataDestination;
import user.DataSource;
import user.UserComputeImpl;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TestUserComputeAPI {

    @Test
    void testSubmitJobReturnsFalseByDefault() {
        StorageComputeAPI mockStorage = Mockito.mock(StorageComputeAPI.class);
        UserComputeImpl userCompute = new UserComputeImpl(mockStorage);

        DataSource mockSource = Mockito.mock(DataSource.class);
        DataDestination mockDest = Mockito.mock(DataDestination.class);

        boolean result = userCompute.submitJob(mockSource, mockDest, ",");
        assertFalse(result, "Default implementation should return false");
    }

    @Test
    void testGetResultsReturnsEmptyList() {
        StorageComputeAPI mockStorage = Mockito.mock(StorageComputeAPI.class);
        UserComputeImpl userCompute = new UserComputeImpl(mockStorage);

        List<String> results = userCompute.getResults();
        assertTrue(results.isEmpty(), "Default implementation should return empty list");
    }
}
