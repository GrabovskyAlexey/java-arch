package ru.grabovsky;

import java.util.ArrayList;
import java.util.List;

/**
 * Request
 *
 * @author grabovsky.alexey
 * @created 02.10.2022 15:58
 */
public class Request {
    private List<Header> headers = new ArrayList<>();
    private List<String> content;
    private String path;
    private HttpMethod method;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }
}
