package ru.grabovsky;

import ru.grabovsky.config.Config;
import ru.grabovsky.domain.HttpRequest;
import ru.grabovsky.domain.HttpResponse;
import ru.grabovsky.logger.ConsoleLogger;
import ru.grabovsky.logger.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RequestHandler implements Runnable {

    private final Config config;

    private static final Logger logger = new ConsoleLogger();

    private final SocketService socketService;

    private final RequestParser requestParser = new RequestParserImpl();
    private final ResponseSerializer responseSerializer = new ResponseSerializerImpl();

    public RequestHandler(SocketService socketService, Config config) {
        this.socketService = socketService;
        this.config = config;
    }

    @Override
    public void run() {

        List<String> request = socketService.readRequest();

        HttpRequest httpRequest = requestParser.parse(request);

        Path path = Paths.get(config.getWwwRoot(), httpRequest.getPath()).toAbsolutePath();

        if (!Files.exists(path)) {
            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "text/html; charset=utf-8");
            List<String> body = new ArrayList<>();
            body.add("<h1>Файл не найден!</h1>\n");
            String rawResponse = responseSerializer.serialize(
                    HttpResponse.builder()
                            .withStatusCode(404)
                            .withHeaders(headers)
                            .withBody(body)
                            .build()
            );
            socketService.writeResponse(rawResponse);
            return;
        }

        try {
            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "text/html; charset=utf-8");
            List<String> body = Files.newBufferedReader(path).lines().collect(Collectors.toList());
            String rawResponse = responseSerializer.serialize(
                    HttpResponse.builder()
                            .withStatusCode(200)
                            .withHeaders(headers)
                            .withBody(body)
                            .build()
            );
            socketService.writeResponse(rawResponse);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        logger.info("Client disconnected!");
    }
}
