package hexlet.code.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(Path pathToFile) throws IOException {
        Map<String, Object> parsedFile = new HashMap<>();
        String file = Files.readString(pathToFile);

        if (pathToFile.toString().endsWith(".json")) {
            return parseJson(file);
        } else if (pathToFile.toString().endsWith(".yaml")
                || pathToFile.toString().endsWith(".yml")) {
            return parseYaml(file);
        }

        return parsedFile;
    }

    private static Map<String, Object> parseYaml(String file) throws IOException {
        ObjectMapper mapper = new YAMLMapper();

        return mapper.readValue(file, HashMap.class);
    }

    private static Map<String, Object> parseJson(String file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(file, HashMap.class);
    }
}
