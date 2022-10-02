package ru.grabovsky;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

/**
 * Service
 *
 * @author grabovsky.alexey
 * @created 02.10.2022 16:05
 */
public class Service {
    private final String WWW_ROOT = "/Users/crazyezh/www";

    public Response getResponseWithContent(String filename) throws IOException {
        Response response = new Response();
        Path path = null;
        if(filename != null) {
            path = Paths.get(WWW_ROOT, filename);
        }
        if (path != null && Files.exists(path)) {
            response.setStatus(HttpStatus.OK);
            response.setContent(
                    Files.newBufferedReader(path).lines()
                            .collect(Collectors.toList())
            );
        } else {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.getContent().add("<h1>Файл не найден!</h1>");
        }
        return response;
    }
}
