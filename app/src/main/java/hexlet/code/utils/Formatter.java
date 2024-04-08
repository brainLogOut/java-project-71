package hexlet.code.utils;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String generateStylishFormat(Map<List<String>, Object> differOfFiles) {
        StringBuilder stylishFormat = new StringBuilder();

        stylishFormat.append("{").append("\n");
        differOfFiles.forEach((key, value) -> {
            String differResult = key.get(0);
            String innerKey = key.get(1);

            stylishFormat.append("  ").append(differResult);
            stylishFormat.append(" ").append(innerKey).append(": ");
            stylishFormat.append(value).append("\n");
        });
        stylishFormat.append("}");

        return stylishFormat.toString();
    }
}
