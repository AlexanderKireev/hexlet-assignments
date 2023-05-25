package exercise;

import java.util.concurrent.CompletableFuture;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

//
import java.io.IOException;
import java.util.stream.Stream;

class App {

    // BEGIN
    public static CompletableFuture<String> unionFiles(String path1, String path2, String resultPath) {
        Path absolutePath1 = Paths.get(path1).toAbsolutePath().normalize();
        Path absolutePath2 = Paths.get(path2).toAbsolutePath().normalize();
        Path absolutePath3 = Paths.get(resultPath).toAbsolutePath().normalize();

        CompletableFuture<String> file1 = CompletableFuture.supplyAsync(() -> {
            var result = "";
            try {
                result = Files.readString(absolutePath1);
            } catch (IOException e) {
                System.out.println("NoSuchFileException");
            }
            return result;
        });

        CompletableFuture<String> file2 = CompletableFuture.supplyAsync(() -> {
            var result = "";
            try {
                result = Files.readString(absolutePath2);
            } catch (IOException e) {
                System.out.println("NoSuchFileException");
            }
            return result;
        });

        CompletableFuture<String> result = file1.thenCombine(file2, (f1, f2) -> {
            try {
                Files.writeString(absolutePath3, f1 + f2, StandardOpenOption.CREATE);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return f1 + f2;
        });

        return result;
    }

    public static CompletableFuture<Long> getDirectorySize(String directory) {
        Path absolutePath = Paths.get(directory).toAbsolutePath().normalize();
        CompletableFuture<Long> result = CompletableFuture.supplyAsync(() -> {
            long count = 0;
            try {
                Stream<Path> files = Files.list(absolutePath);
                count = files.count();
            } catch (IOException e) {
                System.out.println("Directory does not exist");
            }
            return count;
        });
        return result;
    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        CompletableFuture<String> r = unionFiles("src/main/resources/file1.txt", "src/main/resources/file2.txt", "src/main/resources/result.txt");
        System.out.println(r.get());
        // END
    }
}

