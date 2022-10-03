package ru.grabovsky.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpResponse {

    private int statusCode;

    private Map<String, String> headers;

    private List<String> body = new ArrayList<>();

    public HttpResponse() {
    }

    public HttpResponse(int statusCode) {
        this.statusCode = statusCode;
    }

    public HttpResponse(int statusCode, Map<String, String> headers) {
        this.statusCode = statusCode;
        this.headers = headers;
    }

    public HttpResponse(int statusCode, Map<String, String> headers, List<String> body) {
        this.statusCode = statusCode;
        this.headers = headers;
        this.body = body;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public List<String> getBody() {
        return body;
    }

    public void setBody(List<String> body) {
        this.body = body;
    }
}
