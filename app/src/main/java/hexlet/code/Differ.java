package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.*;

public class Differ {
    public static String generate(Path pathToFile1, Path pathToFile2) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        String file1 = Files.readString(pathToFile1);
        String file2 = Files.readString(pathToFile2);
        StringBuilder differResult = new StringBuilder();

        Map<String, Object> parsedFile1 = mapper.readValue(file1, HashMap.class);
        Map<String, Object> parsedFile2 = mapper.readValue(file2, HashMap.class);

        Set<String> keysBothFiles = new HashSet<>(parsedFile1.keySet());
        keysBothFiles.addAll(parsedFile2.keySet());

        List<String> keysBothFilesSorted = keysBothFiles.stream()
                .sorted()
                .toList();

        differResult.append("{").append("\n");
        for (String key : keysBothFilesSorted) {
            boolean file1ContainsKey = parsedFile1.containsKey(key);
            boolean file2ContainsKey = parsedFile2.containsKey(key);
            Object keyValueFile1 = parsedFile1.get(key);
            Object keyValueFile2 = parsedFile2.get(key);

            if (file1ContainsKey && file2ContainsKey
                && keyValueFile1.equals(keyValueFile2)) {

                differResult.append("    ").append(key).append(": ").append(keyValueFile1).append("\n");
            }

            if ((file1ContainsKey && !file2ContainsKey)
                    || (!keyValueFile2.equals(keyValueFile1) && keyValueFile1 != null)) {

                differResult.append("  - ").append(key).append(": ").append(keyValueFile1).append("\n");
            }

            if ((!file1ContainsKey && file2ContainsKey)
                    || (!keyValueFile1.equals(keyValueFile2) && keyValueFile2 != null)) {

                differResult.append("  + ").append(key).append(": ").append(keyValueFile2).append("\n");
            }

        }
        differResult.append("}");

        return differResult.toString();
    }
}
