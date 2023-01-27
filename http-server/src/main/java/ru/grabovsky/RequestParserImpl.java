package ru.grabovsky;

import ru.grabovsky.domain.HttpRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * RequestParserImpl
 *
 * @author grabovsky.alexey
 * @created 03.10.2022 20:11
 */
public class RequestParserImpl implements RequestParser {
    @Override
    public HttpRequest parse(List<String> rawRequest) {
        String[] first = rawRequest.get(0).split(" ");
        Map<String, String> headers = new HashMap<>();
        boolean content = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < rawRequest.size(); i++) {
            String item = rawRequest.get(i);
            if(item.isEmpty()){
                content = true;
                continue;
            }
            if(!content){
                String[] header = item.split(":");
                headers.put(header[0], header[1].trim());
            } else {
                sb.append(item);
            }
        }
        return HttpRequest.builder()
                .withMethod(first[0])
                .withPath(first[1])
                .withHeaders(headers)
                .withBody(sb.toString())
                .build();
    }
}
