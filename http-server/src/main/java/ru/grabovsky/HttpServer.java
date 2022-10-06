package ru.grabovsky;

import ru.grabovsky.config.Config;
import ru.grabovsky.config.ConfigFactory;
import ru.grabovsky.logger.ConsoleLogger;
import ru.grabovsky.logger.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    private static final Logger logger = new ConsoleLogger();

    public static void main(String[] args) {
        Config config = ConfigFactory.getConfig(args);
        try (ServerSocket serverSocket = new ServerSocket(config.getPort())) {
            logger.info("Server started!");

            while (true) {
                Socket socket = serverSocket.accept();
                logger.info("New client connected!");

                new Thread(new RequestHandler(new SocketService(socket), config)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
