package ru.grabovsky;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller
 *
 * @author grabovsky.alexey
 * @created 02.10.2022 15:59
 */
public class Controller {

    private final Service service;
    private final List<Header> okHeaders = new ArrayList<>();
    private final List<Header> notFoundHeaders = new ArrayList<>();

    public Controller() {
        service = new Service();
        notFoundHeaders.add(new Header("HTTP/1.1 404 NOT_FOUND"));
        notFoundHeaders.add(new Header("Content-Type: text/html; charset=utf-8"));
        okHeaders.add(new Header("HTTP/1.1 200 OK"));
        okHeaders.add(new Header("Content-Type: text/html; charset=utf-8"));
    }

    public Response processRequest(Request request) throws IOException {
        Response response = service.getResponseWithContent(request.getPath());
        if (response.getStatus() == HttpStatus.OK) {
            response.setHeaders(okHeaders);
        } else if (response.getStatus() == HttpStatus.NOT_FOUND) {
            response.setHeaders(notFoundHeaders);
        } else {
            throw new RuntimeException("Unknown HTTP Status");
        }
        return response;
    }
}
