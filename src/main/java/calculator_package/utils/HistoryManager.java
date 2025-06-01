package calculator_package.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HistoryManager {
    private final List<String> history;
    private final Path filePath;
    private final DateTimeFormatter formatter;

    public HistoryManager(String fileName) {
        this.history = new ArrayList<>();
        this.filePath = Paths.get(fileName);
        this.formatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Load previous history
        loadHistoryFromFile();
    }

    public void addHistoryEntry(String expression, double result) {
        String timestamp = LocalDateTime.now().format(formatter);
        String entry = String.format("%s | %s = %s", timestamp, expression, result);
        history.add(entry);
        saveEntryToFile(entry); // append to file
    }

    public List<String> getHistory() {
        return new ArrayList<>(history); // return a copy to prevent external modifications
    }

    private void saveEntryToFile(String entry) {
        try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardOpenOption.CREATE,StandardOpenOption.APPEND)) {
            writer.write(entry);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error saving history: " + e.getMessage());
        }
    }
    private void loadHistoryFromFile() {
        if (Files.exists(filePath)) {
            try (BufferedReader reader = Files.newBufferedReader(filePath)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    history.add(line);
                }
            } catch (IOException e) {
                System.err.println("Error loading history: " + e.getMessage());
            }
        }
    }
}
