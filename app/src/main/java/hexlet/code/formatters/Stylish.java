package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {
    public static String generateStylishFormat(Map<List<String>, List<Object>> differOfFiles) {
        StringBuilder stylishFormat = new StringBuilder();

        stylishFormat.append("{").append("\n");
        differOfFiles.forEach((key, value) -> {
            String differResult = key.get(0);
            String innerKey = key.get(1);
            Object keyValueFile1 = value.get(0);
            Object keyValueFile2 = value.get(1);

            stylishFormat.append(generateString(differResult, innerKey, keyValueFile1, keyValueFile2));
        });
        stylishFormat.append("}");

        return stylishFormat.toString();
    }

    private static String generateString(String differResult, String innerKey,
                                         Object keyValueFile1, Object keyValueFile2) {
        StringBuilder result = new StringBuilder();

        switch (differResult) {
            case " " : return result.append("  ").append(" ")
                    .append(" ").append(innerKey).append(": ")
                    .append(keyValueFile1).append("\n").toString();

            case "+" : return result.append("  ").append("+")
                    .append(" ").append(innerKey).append(": ")
                    .append(keyValueFile2).append("\n").toString();

            case "-" : return result.append("  ").append("-")
                    .append(" ").append(innerKey).append(": ")
                    .append(keyValueFile1).append("\n").toString();

            case "-+" : return  result.append("  ").append("-")
                    .append(" ").append(innerKey).append(": ")
                    .append(keyValueFile1).append("\n")
                    .append("  ").append("+")
                    .append(" ").append(innerKey).append(": ")
                    .append(keyValueFile2).append("\n")
                    .toString();

            default: throw new RuntimeException("No supported action!");
        }

    }
}
