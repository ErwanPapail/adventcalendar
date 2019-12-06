import models.Movement;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileLoaderTest {

    private FileLoader fileLoader;

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

        final var firstMovement0 = new Movement("R", 1000);
        final var lastMovement0 = new Movement("R", 999);
        final var firstMovement1 = new Movement("L", 992);
        final var lastMovement1 = new Movement("R", 9);

        assertEquals(filesLines.get(0).get(0), firstMovement0);
        assertEquals(filesLines.get(0).get(filesLines.get(0).size() - 1), lastMovement0);
        assertEquals(filesLines.get(1).get(0), firstMovement1);
        assertEquals(filesLines.get(1).get(filesLines.get(1).size() - 1), lastMovement1);
    }
}