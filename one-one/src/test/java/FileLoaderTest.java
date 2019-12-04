import org.junit.jupiter.api.Test;

import java.io.File;


import static org.junit.jupiter.api.Assertions.*;

class FileLoaderTest {

    FileLoader fileLoader;

    @Test
    void shouldCreateFileLoaderWithCorrectPath() {
        final var path = "input.txt";
        final var file = new File(getClass().getClassLoader().getResource(path).getFile());
        fileLoader = new FileLoader(path);

        assertEquals(fileLoader.file, file);
    }

    @Test
    void shouldLoadFileAsList() {
        final var path = "input.txt";
        fileLoader = new FileLoader(path);
        final var filesLines = fileLoader.getFileContent();

        assertEquals(filesLines.get(0), 140403.f);
        assertEquals(filesLines.get(filesLines.size() - 1), 138385.f);
    }
}