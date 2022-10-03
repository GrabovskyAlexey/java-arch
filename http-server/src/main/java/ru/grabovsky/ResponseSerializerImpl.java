package ru.grabovsky;

import ru.grabovsky.domain.HttpResponse;

import java.util.Map;

/**
 * ResponseSerializerImpl
 *
 * @author grabovsky.alexey
 * @created 03.10.2022 21:49
 */
public class ResponseSerializerImpl implements ResponseSerializer {
    @Override
    public String serialize(HttpResponse httpResponse) {
        StringBuilder sb = new StringBuilder();
        int statusCode = httpResponse.getStatusCode();
        if(statusCode == 200){
            sb.append("HTTP/1.1 200 OK\n");
        } else if (statusCode == 404) {
            sb.append("HTTP/1.1 404 NOT_FOUND\n");
        } else {
            sb.append("HTTP/1.1 400 BAD_REQUEST\n");
        }
        for(Map.Entry entry : httpResponse.getHeaders().entrySet()){
            sb.append(entry.getKey())
                    .append(": ")
                    .append(entry.getValue())
                    .append("\n");
        }
        sb.append("\n");
        httpResponse.getBody().forEach(sb::append);
        return sb.toString();
    }
}
