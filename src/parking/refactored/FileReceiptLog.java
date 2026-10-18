package parking.refactored;

import java.io.FileWriter;
import java.io.IOException;
import java.io.UncheckedIOException;

public class FileReceiptLog implements ReceiptLog {
    private final String fileName;

    public FileReceiptLog(String fileName) { this.fileName = fileName; }

    public void record(String line) {
        try (FileWriter fw = new FileWriter(fileName, true)) {
            fw.write(line + System.lineSeparator());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}