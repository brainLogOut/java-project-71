package hexlet.code;

//import jdk.jfr.StackTrace;
import picocli.CommandLine;
import picocli.CommandLine.Help.ColorScheme;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

//import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
//import java.security.MessageDigest;
import java.util.concurrent.Callable;

import static hexlet.code.Differ.generate;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 0.01",
        description = "Compares two configuration files and shows a difference.")
class App implements Callable<Integer> {

    @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file", defaultValue = "")
    private String pathFile1;

    @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file", defaultValue = "")
    private String pathFile2;

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]", paramLabel = "format",
            defaultValue = "stylish")
    private String format = "stylish";

    @Override
    public Integer call() throws IOException { // your business logic goes here...

        Path pathToFile1 = Paths.get(pathFile1).toAbsolutePath().normalize();
        Path pathToFile2 = Paths.get(pathFile2).toAbsolutePath().normalize();

        if (Files.notExists(pathToFile1)) {
            throw new RuntimeException(pathToFile1 + " is no exist!");
        } else if (Files.notExists(pathToFile2)) {
            throw new RuntimeException(pathToFile2 + " is no exist!");
        }

        try {
            String differResult = generate(pathToFile1, pathToFile2, format);
            System.out.println(differResult);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static void main(String... args) {
        ColorScheme colorScheme = new ColorScheme.Builder()
                .options(CommandLine.Help.Ansi.Style.bold, CommandLine.Help.Ansi.Style.fg_red)
                .parameters(CommandLine.Help.Ansi.Style.bold, CommandLine.Help.Ansi.Style.fg_green)
                .build();

        new CommandLine(new App())
                .setColorScheme(colorScheme)
                .execute(args);
    }
}
