package ru.grabovsky.handler;

import ru.grabovsky.*;
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
    private final RequestParser requestParser;
    private final ResponseSerializer responseSerializer;

    public RequestHandler(final SocketService socketService,
                          final Config config) {
        this.socketService = socketService;
        this.config = config;
        this.requestParser = new RequestParserImpl();
        this.responseSerializer = new ResponseSerializerImpl();
    }

    @Override
    public void run() {

        List<String> request = socketService.readRequest();

        HttpRequest httpRequest = requestParser.parse(request);

        final MethodHandler methodHandler = MethodHandlerFactory.create(socketService, responseSerializer, config);
        methodHandler.handle(httpRequest);


        try{
            socketService.close();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        logger.info("Client disconnected!");
    }
}
