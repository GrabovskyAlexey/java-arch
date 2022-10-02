package ru.grabovsky;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Dispatcher
 *
 * @author grabovsky.alexey
 * @created 02.10.2022 15:58
 */
public class Dispatcher {
    private final PrintWriter writer;
    private final BufferedReader reader;
    private final Controller controller;

    public Dispatcher(BufferedReader reader, PrintWriter writer) {
        this.writer = writer;
        this.reader = reader;
        this.controller = new Controller();
    }

    public void dispatch() throws IOException {
        Request request = read();
        send(controller.processRequest(request));
    }

    private void send(Response response){
        for(Header header: response.getHeaders()){
            writer.println(header.getHeader());
        }
        writer.println();
        for(String content: response.getContent()){
            writer.println(content);
        }
    }

    private Request read() throws IOException {
        Request request = new Request();
        boolean isContent = false;
        while (reader.ready()) {
            String line = reader.readLine();
            if(line.isEmpty()){
                isContent = true;
                continue;
            }
            if (isContent) {
                request.getContent().add(line);
            } else {
                request.getHeaders().add(new Header(line));
            }
        }
        List<Header> headers = request.getHeaders();
        if(!headers.isEmpty()) {
            String[] firstHeader = headers.get(0).getHeader().split(" ");
            request.setMethod(HttpMethod.valueOf(firstHeader[0]));
            request.setPath(firstHeader[1]);
        }
        return request;
    }
}
