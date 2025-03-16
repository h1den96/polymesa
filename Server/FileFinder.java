package Server;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
//import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileFinder {
    
    public static List<String> findFiles(Path path, String[] fileExtensions, String basePath) throws IOException {

        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException("Path must be a directory!");
        }

        List<String> result;
        try (Stream<Path> walk = Files.walk(path, 1)) {
            result = walk
                    .filter(p -> !Files.isDirectory(p))
                    .map(Path::toString)
                    .filter(f -> isEndWith(f.toLowerCase(), fileExtensions))
                    .map(f -> f.replace(basePath, ""))
                    .collect(Collectors.toList());
        }
        return result;
    }

    private static boolean isEndWith(String file, String[] fileExtensions) {
        boolean result = false;
        for (String fileExtension : fileExtensions) {
            if (file.endsWith(fileExtension)) {
                result = true;
                break;
            }
        }
        return result;
    }
}