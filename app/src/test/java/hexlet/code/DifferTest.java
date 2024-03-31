package hexlet.code;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
//import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DifferTest {
//    static File firstJsonFile = new File("src/test/resources/1.json");
//    static File secondJsonFile = new File("src/test/resources/2.json");
    static Path firstJsonFile = Paths.get("src/test/resources/1.json").toAbsolutePath();
    static Path secondJsonFile = Paths.get("src/test/resources/2.json").toAbsolutePath();

    @BeforeAll
    public static void filesInit() throws IOException {
        String json1 = "{\n"
                + "  \"host\": \"hexlet.io\",\n"
                + "  \"timeout\": 50,\n"
                + "  \"proxy\": \"123.234.53.22\",\n"
                + "  \"follow\": false\n"
                + "}";
        String json2 = "{\n"
                + "  \"timeout\": 20,\n"
                + "  \"verbose\": true,\n"
                + "  \"host\": \"hexlet.io\"\n"
                + "}";

        Files.writeString(firstJsonFile, json1);
        Files.writeString(secondJsonFile, json2);

//        Files.writeString(firstJsonFile.toPath().toAbsolutePath(), json1);
//        Files.writeString(secondJsonFile.toPath().toAbsolutePath(), json2);
    }

    @AfterAll
    public static void filesDelete() {
        firstJsonFile.toFile().delete();
        secondJsonFile.toFile().delete();
    }

    @Test
    public void differTest() throws IOException {
        String expected = "{\n"
                + "  - follow: false\n"
                + "    host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}";
//        Path path1 = Paths.get(firstJsonFile.getAbsolutePath());
//        Path path2 = Paths.get(secondJsonFile.getAbsolutePath());
//        String actual = Differ.generate(path1, path2);
        String actual = Differ.generate(firstJsonFile, secondJsonFile);

        assertEquals(expected, actual);
    }
}
