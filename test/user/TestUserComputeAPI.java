package user;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TestUserComputeAPI {
    @Test
    void testUserComputeAPIMockMethods() {
        UserComputeAPI mockAPI = Mockito.mock(UserComputeAPI.class);
        DataSource mockSource = Mockito.mock(DataSource.class);
        DataDestination mockDest = Mockito.mock(DataDestination.class);

        Mockito.when(mockAPI.setInputSource(mockSource)).thenReturn(mockSource);
        Mockito.when(mockAPI.setOutputDestination(mockDest)).thenReturn(mockDest);
        Mockito.when(mockAPI.setDelimiters(",")).thenReturn(",");
        Mockito.when(mockAPI.submitJob(mockSource, mockDest, ",")).thenReturn(true);
        Mockito.when(mockAPI.executeJob(mockSource)).thenReturn(mockSource);
        Mockito.when(mockAPI.getResults()).thenReturn(List.of("result1", "result2"));

        assertTrue(mockAPI.setInputSource(mockSource) == mockSource, "setInputSource should return the mock source");
        assertTrue(mockAPI.setOutputDestination(mockDest) == mockDest, "setOutputDestination should return the mock destination");
        assertTrue(",".equals(mockAPI.setDelimiters(",")), "setDelimiters should return the delimiter");
        assertTrue(mockAPI.submitJob(mockSource, mockDest, ","), "submitJob should return true as mocked");
        assertTrue(mockAPI.executeJob(mockSource) == mockSource, "executeJob should return the mock source");
        List<String> results = mockAPI.getResults();
        assertTrue(results.size() == 2 && results.contains("result1") && results.contains("result2"), "getResults should return mocked results");
    }
}