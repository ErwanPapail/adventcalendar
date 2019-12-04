import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileLoader {

    final File file;

    public FileLoader(final String resource) {
        this.file = new File(getClass().getClassLoader().getResource(resource).getFile());
        this.file.setReadable(true);
    }

    public List getFileContent() {
        List lines = new ArrayList<>();

        try(final var reader = new FileReader("C:\\Users\\Erwan PAPAIL\\IdeaProjects\\advent-calendar\\one-one\\target\\classes\\input.txt");
            final var br = new BufferedReader(reader)) {

            String line;
            while ((line = br.readLine()) != null) {
                lines.add(Float.parseFloat(line));
            }
        } catch (Exception e) {e.printStackTrace();}
        return lines;
    }
}
