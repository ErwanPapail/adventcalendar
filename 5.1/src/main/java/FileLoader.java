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
        List<String> inputCodeParsed = new ArrayList<>();

        try (final var reader = new FileReader("C:\\Users\\Nelly\\IdeaProjects\\adventcalendar\\5.1\\target\\classes\\input.txt");
             final var br = new BufferedReader(reader)) {

            var inputCodeRaw = br.readLine();
            inputCodeParsed = List.of(inputCodeRaw.split(","));

            return inputCodeParsed.stream()
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputCodeParsed;
    }
}
