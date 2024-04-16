package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Plain {
    public static String generatePlainFormat(Map<List<String>, List<Object>> differOfFiles) {
        StringBuilder plainFormat = new StringBuilder();

        differOfFiles.forEach((key, value) -> {
            String differResult = key.get(0);
            String innerKey = key.get(1);
            Object keyValueFile1 = value.get(0);
            Object keyValueFile2 = value.get(1);

            if (!differResult.equals(" ")) {
                plainFormat.append("Property");
                plainFormat.append(" '").append(innerKey).append("' was ");
                plainFormat.append(generateEnd(differResult, keyValueFile1, keyValueFile2));
                plainFormat.append("\n");
            }
        });
        plainFormat.deleteCharAt(plainFormat.length() - 1);

        return plainFormat.toString();
    }

    private static String generateEnd(String differResult, Object keyValueFile1, Object keyValueFile2) {
        StringBuilder result = new StringBuilder();

        switch (differResult) {
            case "+" : return result.append("added with value: ").append(transformValue(keyValueFile2)).toString();
            case "-" : return "removed";
            case "-+" : return  result.append("updated. From ")
                    .append(transformValue(keyValueFile1))
                    .append(" to ").append(transformValue(keyValueFile2)).toString();
            default: throw new RuntimeException("No supported action!");
        }
    }

    private static String transformValue(Object keyValueFile2) {
        String valueInString = keyValueFile2.toString();

        if (valueInString.startsWith("{") || valueInString.startsWith("[")) {
            return "[complex value]";
        } else if (keyValueFile2.getClass().toString().equals("class java.lang.String")
                && !keyValueFile2.equals("null")) {
            return "'" + valueInString + "'";
        } else {
            return valueInString;
        }
    }
}
