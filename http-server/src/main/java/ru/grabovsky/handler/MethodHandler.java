package ru.grabovsky.handler;

import ru.grabovsky.HttpStatus;
import ru.grabovsky.ResponseSerializer;
import ru.grabovsky.SocketService;
import ru.grabovsky.config.Config;
import ru.grabovsky.domain.HttpRequest;
import ru.grabovsky.domain.HttpResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class MethodHandler {
    private final String method;
    private final MethodHandler next;

    protected final SocketService socketService;
    protected final ResponseSerializer responseSerializer;
    protected final Config config;

    protected MethodHandler(final String method, final MethodHandler next, final SocketService socketService, final ResponseSerializer responseSerializer, final Config config) {
        this.method = method;
        this.next = next;
        this.socketService = socketService;
        this.responseSerializer = responseSerializer;
        this.config = config;
    }

    public void handle(HttpRequest request) {
        HttpResponse response;
        if (method.equals(request.getMethod())) {
            response = internalHandle(request);
        } else if (next != null) {
            next.handle(request);
            return;
        } else {
            Map<String, String> headers = new HashMap<>();
            List<String> body = new ArrayList<>();
            body.add("<h1>Метод не поддерживается</h1>");
            headers.put("Content-Type", "text/html; charset=utf-8");
            response = HttpResponse.builder()
                    .withStatusCode(HttpStatus.METHOD_NOT_ALLOWED)
                    .withHeaders(headers)
                    .withBody(body)
                    .build();
        }
        String rawResponse = responseSerializer.serialize(response);
        socketService.writeResponse(rawResponse);
    }

    protected abstract HttpResponse internalHandle(HttpRequest request);
}
