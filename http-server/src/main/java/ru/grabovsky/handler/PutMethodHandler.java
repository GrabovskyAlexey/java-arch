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

@Handler(order = 4)
public class PutMethodHandler extends MethodHandler {

    protected PutMethodHandler(final MethodHandler next, final SocketService socketService, final ResponseSerializer responseSerializer, final Config config) {
        super("PUT", next, socketService, responseSerializer, config);
    }

    @Override
    protected HttpResponse internalHandle(final HttpRequest request) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "text/html; charset=utf-8");
        List<String> body = new ArrayList<>();
        body.add("<h1>Метод PUT</h1>\n");

        return HttpResponse.builder()
                .withStatusCode(HttpStatus.OK)
                .withHeaders(headers)
                .withBody(body)
                .build();
    }
}
