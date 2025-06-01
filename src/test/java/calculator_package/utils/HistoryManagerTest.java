package calculator_package.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class HistoryManagerTest {

    // create a temporary file for testing
    private static final String TEST_FILE = "test_history.txt";
    // the object we are testing
    private HistoryManager historyManager;

    @BeforeEach
    void setUp() throws IOException {
        // make sure the file does not exist before each testing
        Files.deleteIfExists(Paths.get(TEST_FILE));

        // create a new history manager that uses the test file
        historyManager = new HistoryManager(TEST_FILE);
    }

    @AfterEach
    void tearDown() throws IOException {
        // clean up deleting the file after each test
        Files.deleteIfExists(Paths.get(TEST_FILE));
    }

    @Test
    void addHistoryEntry_addsToMemoryAndFIleTest() throws IOException {
        // add a sample history entry
        String expression = "2 + 2";
        double result = 4.0;
        historyManager.addHistoryEntry(expression, result);

        // Get the history list from memory
        List<String> history = historyManager.getHistory();

        // Verify it is stored in the memory
        List<String> lines = Files.readAllLines(Paths.get(TEST_FILE));
        assertEquals(1,lines.size(),"File should have 1 lines");
        assertTrue(lines.get(0).contains(expression)); // file should contain the expression
        assertTrue(lines.get(0).contains(String.valueOf(result))); // file should contain the result
    }

    @Test
    void testLoadHistoryFromFile_readsHistoryFromFile() throws IOException {
        // simulate an existing file with one entry
        String simulatedEntry = "2025-05-31 12:00:00 | 5 * % = 25.0";
        Files.write(Paths.get(TEST_FILE), List.of(simulatedEntry), StandardOpenOption.CREATE);

        // recreate history manager to trigger file loading
        HistoryManager loadManager = new HistoryManager(TEST_FILE);
        List<String> history = loadManager.getHistory();

        assertEquals(1, history.size(), "File should have 1 lines");
        assertEquals(simulatedEntry, history.get(0), "Loaded entry should match file contents");
    }

}
