package ru.grabovsky.handler;

import ru.grabovsky.HttpStatus;
import ru.grabovsky.ResponseSerializer;
import ru.grabovsky.SocketService;
import ru.grabovsky.config.Config;
import ru.grabovsky.domain.HttpRequest;
import ru.grabovsky.domain.HttpResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Handler(order = 1)
public class GetMethodHandler extends MethodHandler {

    protected GetMethodHandler(final MethodHandler next, final SocketService socketService, final ResponseSerializer responseSerializer, final Config config) {
        super("GET", next, socketService, responseSerializer, config);
    }

    @Override
    protected HttpResponse internalHandle(final HttpRequest request) {
        Path path = Paths.get(config.getWwwRoot(), request.getPath()).toAbsolutePath();

        if (!Files.exists(path)) {
            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "text/html; charset=utf-8");
            List<String> body = new ArrayList<>();
            body.add("<h1>Файл не найден!</h1>\n");

            return HttpResponse.builder()
                    .withStatusCode(HttpStatus.NOT_FOUND)
                    .withHeaders(headers)
                    .withBody(body)
                    .build();
        }

        try {
            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "text/html; charset=utf-8");
            List<String> body = Files.newBufferedReader(path).lines().collect(Collectors.toList());
            return HttpResponse.builder()
                    .withStatusCode(HttpStatus.OK)
                    .withHeaders(headers)
                    .withBody(body)
                    .build();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
