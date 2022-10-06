package ru.grabovsky.domain;

import java.util.Map;

public class HttpRequest {

    private String method;

    private String path;

    private Map<String, String> headers;

    private String body;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public static Builder builder(){
        return new Builder();
    }


    public static class Builder{
        private final HttpRequest request;
        private Builder(){
            this.request = new HttpRequest();
        }
        public Builder withMethod(String method) {
            request.method = method;
            return this;
        }

        public Builder withPath(String path) {
            request.path = path;
            return this;
        }

        public Builder withHeaders(Map<String, String> headers) {
            request.headers = headers;
            return this;
        }

        public Builder withBody(String body) {
            request.body = body;
            return this;
        }

        public HttpRequest build(){
            return request;
        }
    }
}
