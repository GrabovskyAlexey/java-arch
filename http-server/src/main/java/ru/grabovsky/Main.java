package ru.grabovsky;

/**
 * Main
 *
 * @author grabovsky.alexey
 * @created 02.10.2022 15:55
 */
public class Main {
    public static void main(String[] args) {
        HttpServer server = new HttpServer();
        server.start(9000);
    }
}
