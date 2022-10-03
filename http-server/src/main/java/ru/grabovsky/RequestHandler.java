package ru.grabovsky;

import ru.grabovsky.domain.HttpRequest;
import ru.grabovsky.domain.HttpResponse;
import ru.grabovsky.logger.ConsoleLogger;
import ru.grabovsky.logger.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RequestHandler implements Runnable {

    private static final String WWW = "/Users/crazyezh/www";

    private static final Logger logger = new ConsoleLogger();

    private final SocketService socketService;

    private final RequestParser requestParser = new RequestParserImpl();
    private final ResponseSerializer responseSerializer = new ResponseSerializerImpl();

    public RequestHandler(SocketService socketService) {
        this.socketService = socketService;
    }

    @Override
    public void run() {

        List<String> request = socketService.readRequest();

        HttpRequest httpRequest = requestParser.parse(request);
        String[] parts = request.get(0).split(" ");

        Path path = Paths.get(WWW, httpRequest.getPath());

        HttpResponse response = new HttpResponse();
        if (!Files.exists(path)) {
            response.setStatusCode(404);
            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "text/html; charset=utf-8");
            response.setHeaders(headers);
            response.getBody().add("<h1>Файл не найден!</h1>\n");
            String rawResponse = responseSerializer.serialize(response);
            socketService.writeResponse(rawResponse);
            return;
        }

        try {
            response.setStatusCode(200);
            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "text/html; charset=utf-8");
            response.setHeaders(headers);
            response.setBody(Files.newBufferedReader(path).lines().collect(Collectors.toList()));
            String rawResponse = responseSerializer.serialize(response);
            socketService.writeResponse(rawResponse);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        logger.info("Client disconnected!");
    }
}
