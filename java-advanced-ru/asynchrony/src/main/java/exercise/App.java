package exercise;

import exercise.exception.NoSuchFileException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.Arrays;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.File;
import java.nio.file.StandardOpenOption;
import java.util.stream.Stream;

class App {

    // BEGIN
    private static CompletableFuture<String> getFileContent(String filePath) {
        return CompletableFuture.supplyAsync(() -> {
            Path file = Path.of(filePath);
            if (!Files.exists(file)) {
                throw new NoSuchFileException("File '" + filePath + "' not exists");
            }
            try {
                return Files.readString(file, StandardCharsets.UTF_8);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static CompletableFuture<Void> unionFiles(
            String firstFilePath, String secondFilePath, String resultFilePath) {

        CompletableFuture<String> futureFirstFile = getFileContent(firstFilePath);
        CompletableFuture<String> futureSecondFile = getFileContent(secondFilePath);

        return futureFirstFile.thenAcceptBoth(futureSecondFile, (first, second) -> {
            try {
                Path file = Path.of(resultFilePath);
                if (!Files.exists(file)) {
                    Files.deleteIfExists(Path.of("test.tmp"));
                    file = Files.createFile(Path.of("test.tmp"));
                }
                Files.writeString(file, String.format("%s%n%s", first.trim(), second.trim()), StandardCharsets.UTF_8);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // Обработка исключений
            // Если при работе задач возникли исключения
            // их можно обработать в методе exceptionally
        }).exceptionally(ex -> {
            System.out.println("Oops! We have an exception - " + ex.getMessage());
            return null;
        });
    }

    public static CompletableFuture<Long> getDirectorySize(String directoryPath) {
        return CompletableFuture.supplyAsync(() -> {
            Path directory = Path.of(directoryPath);
            if (!Files.exists(directory) || !Files.isDirectory(directory)) {
                return null;
            }
            try (Stream<Path> stream = Files.list(directory)) {
                return stream
                        .filter(file -> !Files.isDirectory(file))
                        .map(v -> {
                            try {
                                return Files.size(v);
                            } catch (IOException e) {
                                return 0L;
                            }
                        })
                        .reduce(0L, Long::sum);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void main(String[] args) throws Exception {
        // BEGIN
        Path path = Path.of("src/test/resources/file1.txt");
        System.out.println(path);

        File a = new File("/");
        File b = new File("src/main/java/resource/1.txt");
        File c = new File("./");
        File d = new File(".");
        System.out.println(a.getAbsolutePath());
        System.out.println(b.getAbsolutePath());
        System.out.println(c.getAbsolutePath());
        System.out.println(d.getAbsolutePath());

        unionFiles("src/test/resources/file1.txt",
                "src/test/resources/file2.txt",
                "src/test/resources/test.tmp").get();

        System.out.println(getDirectorySize("src/test/resources/dir").get());
        System.out.println(getDirectorySize("src/test/resources/dir/nested").get());
        System.out.println(getDirectorySize("src/test/resources/empty_dir").get());
        // END
    }
}

