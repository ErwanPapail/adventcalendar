import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class FileLoader {

    final File file;

    public FileLoader(final String resource) {
        this.file = new File(getClass().getClassLoader().getResource(resource).getFile());
        this.file.setReadable(true);
    }

    public List<List> getFileContent() {
        List<List> lines = new ArrayList(new ArrayList<Movement>());

        try (final var reader = new FileReader("C:\\Users\\Erwan PAPAIL\\IdeaProjects\\advent-calendar\\three-one\\target\\classes\\input.txt");
             final var br = new BufferedReader(reader)) {

            var line = "";
            while ((line = br.readLine()) != null) {
                lines.add(Arrays.stream(line.split(","))
                        .map(movement -> new Movement(movement.substring(0,1), Integer.parseInt(movement.substring(1))))
                        .collect(Collectors.toList()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }
}
