package hexlet.code;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;


//import com.fasterxml.jackson.core.*;
//import com.fasterxml.jackson.annotation.*;
import hexlet.code.utils.Parser;
import hexlet.code.utils.Formatter;

public class Differ {
    public static String generate(Path pathToFile1, Path pathToFile2, String format) throws IOException {
        Map<String, Object> parsedFile1 = Parser.parse(pathToFile1);
        Map<String, Object> parsedFile2 = Parser.parse(pathToFile2);

        Set<String> keysBothFiles = new HashSet<>(parsedFile1.keySet());
        keysBothFiles.addAll(parsedFile2.keySet());

        List<String> keysBothFilesSorted = keysBothFiles.stream()
                .sorted()
                .toList();

        Map<List<String>, Object> differOfFiles = generateDiffer(keysBothFilesSorted, parsedFile1, parsedFile2);

        return Formatter.generateStylishFormat(differOfFiles);
    }

    private static Map<List<String>, Object> generateDiffer(List<String> keysBothFilesSorted,
                                                            Map<String, Object> parsedFile1,
                                                            Map<String, Object> parsedFile2) {

        Map<List<String>, Object> differOfFiles = new LinkedHashMap<>();

        for (String key : keysBothFilesSorted) {
            boolean file1ContainsKey = parsedFile1.containsKey(key);
            boolean file2ContainsKey = parsedFile2.containsKey(key);
            Object keyValueFile1;
            Object keyValueFile2;

            if (parsedFile1.containsKey(key) && parsedFile1.get(key) == null) {
                keyValueFile1 = "null";
            } else {
                keyValueFile1 = parsedFile1.get(key);
            }

            if (parsedFile2.containsKey(key) && parsedFile2.get(key) == null) {
                keyValueFile2 = "null";
            } else {
                keyValueFile2 = parsedFile2.get(key);
            }

            if (file1ContainsKey && file2ContainsKey
                && keyValueFile1.equals(keyValueFile2)) {

                List<String> complexKey = new ArrayList<>();
                complexKey.add(" ");
                complexKey.add(key);
                differOfFiles.put(complexKey, keyValueFile1);
            }

            if ((file1ContainsKey && !file2ContainsKey)
                    || (!keyValueFile2.equals(keyValueFile1) && keyValueFile1 != null)) {

                List<String> complexKey = new ArrayList<>();
                complexKey.add("-");
                complexKey.add(key);
                differOfFiles.put(complexKey, keyValueFile1);
            }

            if ((!file1ContainsKey && file2ContainsKey)
                    || (!keyValueFile1.equals(keyValueFile2) && keyValueFile2 != null)) {

                List<String> complexKey = new ArrayList<>();
                complexKey.add("+");
                complexKey.add(key);
                differOfFiles.put(complexKey, keyValueFile2);
            }
        }

        return differOfFiles;
    }
}
