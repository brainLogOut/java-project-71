package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.security.MessageDigest;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 0.01",
        description = "Compares two configuration files and shows a difference.")
class App implements Callable<Integer> {

    @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file", defaultValue = "")
    private File file1 = new File("/etc/hosts");

    @Parameters(index = "0", paramLabel = "filepath2", description = "path to second file", defaultValue = "")
    private File file2 = new File("/etc/hosts");

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]", paramLabel = "format",
            defaultValue = "stylish")
    private String format = "stylish";

    @Override
    public Integer call() throws Exception { // your business logic goes here...

        return 0;
    }

    public static void main(String... args) {
        CommandLine.Help.ColorScheme colorScheme = new CommandLine.Help.ColorScheme.Builder()
                .options(CommandLine.Help.Ansi.Style.bold ,CommandLine.Help.Ansi.Style.fg_red)
                .parameters(CommandLine.Help.Ansi.Style.bold ,CommandLine.Help.Ansi.Style.fg_green)
                .build();

        new CommandLine(new App())
                .setColorScheme(colorScheme)
                .execute(args);
    }
}
