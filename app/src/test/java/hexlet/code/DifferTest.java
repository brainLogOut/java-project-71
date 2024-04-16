package hexlet.code;

//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

//import java.io.File;
import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DifferTest {
    static Path firstJsonFile = Paths.get("./src/test/resources/1d.json").toAbsolutePath().normalize();
    static Path secondJsonFile = Paths.get("./src/test/resources/2d.json").toAbsolutePath().normalize();
    static Path firstYamlFile = Paths.get("./src/test/resources/1d.yml").toAbsolutePath().normalize();
    static Path secondYamlFile = Paths.get("./src/test/resources/2d.yml").toAbsolutePath().normalize();
    static Path differFirstToSecondStylish =
            Paths.get("./src/test/resources/1differ2d_Stylish.txt").toAbsolutePath().normalize();
    static Path differFirstToSecondPlain =
            Paths.get("./src/test/resources/1differ2d_Plain.txt").toAbsolutePath().normalize();

    @Test
    public void differTestStylishJson() throws IOException {
        String expected = Files.readString(differFirstToSecondStylish);
        String actual = Differ.generate(firstJsonFile, secondJsonFile, "stylish");

        assertEquals(expected, actual);
    }

    @Test
    public void differTestStylishYaml() throws IOException {
        String expected = Files.readString(differFirstToSecondStylish);
        String actual = Differ.generate(firstYamlFile, secondYamlFile, "stylish");

        assertEquals(expected, actual);
    }

    @Test
    public void differTestPlainJson() throws IOException {
        String expected = Files.readString(differFirstToSecondPlain);
        String actual = Differ.generate(firstJsonFile, secondJsonFile, "plain");

        assertEquals(expected, actual);
    }

    @Test
    public void differTestPlainYaml() throws IOException {
        String expected = Files.readString(differFirstToSecondPlain);
        String actual = Differ.generate(firstYamlFile, secondYamlFile, "plain");

        assertEquals(expected, actual);
    }
}
