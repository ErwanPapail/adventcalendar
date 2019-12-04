import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileLoader {

    final File file;

    public FileLoader(final String resource) {
        this.file = new File(getClass().getClassLoader().getResource(resource).getFile());
        this.file.setReadable(true);
    }

    public List getFileContent() {
        List<String> lines = new ArrayList<>();

        try(final var reader = new FileReader("C:\\Users\\Erwan PAPAIL\\IdeaProjects\\advent-calendar\\two-two\\target\\classes\\input.txt");
            final var br = new BufferedReader(reader)) {

            var totalLine = "";
            var line = "";
            while ((line = br.readLine()) != null) {
                totalLine += line;
            }

            lines = List.of(totalLine.split(","));
            return lines.stream()
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (Exception e) {e.printStackTrace();}
        return lines;
    }
}
