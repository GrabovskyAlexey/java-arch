package ru.grabovsky.config;

import java.io.IOException;
import java.util.Properties;

class ConfigFromFile implements Config{
    private final int port;
    private final String wwwRoot;

    public ConfigFromFile(String filename) {
        Properties props = new Properties();
        try {
            props.load(getClass().getResourceAsStream(filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.port = Integer.parseInt(props.getProperty("port"));
        this.wwwRoot = props.getProperty("www.root");
    }

    @Override
    public int getPort() {
        return this.port;
    }

    @Override
    public String getWwwRoot() {
        return this.wwwRoot;
    }
}
