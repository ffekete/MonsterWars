package monsterwars.reader;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

/**
 * Reads a world map txt file and returns its content as a {@link Set}.
 */
public class WorldMapFileReader {

    /**
     * Reads the given file and returns the lines in a set format.
     * @param fileName what to read.
     * @return the lines of the file.
     * @throws IOException when file is not readable or not found.
     */
    public Set<String> read(final String fileName) throws IOException {
        Set<String> result = new TreeSet<>();
        try (BufferedReader bufferedReader = getBufferedReader(getDataInputStream(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                processLine(result, line);
            }
        }
        return result;
    }

    private BufferedReader getBufferedReader(final DataInputStream dataInputStream) {
        return new BufferedReader(new InputStreamReader(dataInputStream));
    }

    private DataInputStream getDataInputStream(final String fileName) {
        return new DataInputStream(getResourceAsStream(fileName));
    }

    private void processLine(final Set<String> result, String line) {
        if (!line.isEmpty()) {
            result.add(line.trim());
        }
    }

    private InputStream getResourceAsStream(final String fileName) {
        return this.getClass().getClassLoader().getResourceAsStream(fileName);
    }
}
