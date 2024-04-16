package hexlet.code;

import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String format(String formatter, Map<List<String>, List<Object>> differOfFiles) {

        switch (formatter) {
            case "stylish" -> {
                return Stylish.generateStylishFormat(differOfFiles);
            }
            case "plain" -> {
                return Plain.generatePlainFormat(differOfFiles);
            }
            default -> throw new RuntimeException("Unsupported format");
        }
    }
}
