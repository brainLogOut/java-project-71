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
    static Path firstJsonFile = Paths.get("./src/test/resources/1.json").toAbsolutePath().normalize();
    static Path secondJsonFile = Paths.get("./src/test/resources/2.json").toAbsolutePath().normalize();
    static Path firstYamlFile = Paths.get("./src/test/resources/1.yaml").toAbsolutePath().normalize();
    static Path secondYamlFile = Paths.get("./src/test/resources/2.yaml").toAbsolutePath().normalize();
    static Path differFirstToSecond = Paths.get("./src/test/resources/1differ2.txt").toAbsolutePath().normalize();

    @Test
    public void differTestJson() throws IOException {
        String expected = Files.readString(differFirstToSecond);
        String actual = Differ.generate(firstJsonFile, secondJsonFile);

        assertEquals(expected, actual);
    }

    @Test
    public void differTestYaml() throws IOException {
        String expected = Files.readString(differFirstToSecond);
        String actual = Differ.generate(firstYamlFile, secondYamlFile);

        assertEquals(expected, actual);
    }
}
