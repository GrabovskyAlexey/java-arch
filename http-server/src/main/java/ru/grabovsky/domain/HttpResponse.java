package ru.grabovsky.domain;

import ru.grabovsky.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpResponse {

    private HttpStatus statusCode;

    private Map<String, String> headers;

    private List<String> body = new ArrayList<>();

    public HttpResponse() {
    }


    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final HttpResponse response;

        private Builder() {
            this.response = new HttpResponse();
        }

        public Builder withStatusCode(HttpStatus statusCode) {
            response.statusCode = statusCode;
            return this;
        }

        public Builder withHeaders(Map<String, String> headers) {
            response.headers = headers;
            return this;
        }

        public Builder withBody(List<String> body) {
            response.body = body;
            return this;
        }

        public HttpResponse build(){
            return response;
        }
    }
}
