package ru.grabovsky;

import java.util.ArrayList;
import java.util.List;

/**
 * Response
 *
 * @author grabovsky.alexey
 * @created 02.10.2022 15:58
 */
public class Response {
    private List<Header> headers = new ArrayList<>();
    private List<String> content = new ArrayList<>();

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    private HttpStatus status;

    public List<Header> getHeaders() {
        return headers;
    }

    public void setHeaders(List<Header> headers) {
        this.headers = headers;
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }
}
