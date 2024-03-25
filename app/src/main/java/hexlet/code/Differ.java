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

        Map<String, String> parsedFile1 = mapper.readValue(file1, HashMap.class);
        Map<String, String> parsedFile2 = mapper.readValue(file2, HashMap.class);

        Set<String> keysBothFiles = new HashSet<>(parsedFile1.keySet());
        keysBothFiles.addAll(parsedFile2.keySet());

        List<String> keysBothFilesSorted = keysBothFiles.stream()
                        .sorted()
                                .toList();

        String test = parsedFile1.get("follow");
        System.out.println(test);

//        differResult.append("{").append("\n");
//        for (String key : keysBothFilesSorted) {
//            if (parsedFile1.containsKey(key)) {
//
//                differResult.append("\t" ).append(" ").append(" ").append(key).append(": ").append(parsedFile1.get(key));
//
//            }
//
//            if ((!parsedFile1.containsKey(key) && parsedFile2.containsKey(key))
//                    || (!parsedFile1.get(key).equals(parsedFile2.get(key)))) {
//
//                differResult.append("\t" ).append("-").append(" ").append(key).append(": ").append(parsedFile1.get(key));
//
//            }
//
//            if ((parsedFile2.containsKey(key) && parsedFile1.containsKey(key))
//                    || (!parsedFile1.get(key).equals(parsedFile2.get(key)))) {
//
//                differResult.append("\t" ).append("-").append(" ").append(key).append(": ").append(parsedFile1.get(key));
//
//            }
//        }
//        differResult.append("}").append("\n");
//
//        System.out.println(pathToFile1);
//        System.out.println(parsedFile1);
//        System.out.println();
//
//        System.out.println(pathToFile2);
//        System.out.println(parsedFile2);
//        System.out.println();
//
//        System.out.println(keysBothFiles);
//        System.out.println(keysBothFilesSorted);
//
//        System.out.println(differResult);

//        System.out.println(text1);
//        System.out.println(text2);

//        var test = parsedFile1.get("follow");
//        System.out.println(test);
//        System.out.println(parsedFile1);

        return "";
    }
}
