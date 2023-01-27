package ru.grabovsky.config;

class ConfigFromCli implements Config{
    private final int port;
    private final String wwwRoot;

    public ConfigFromCli(String[] args) {
        this.port = Integer.parseInt(args[1]);
        this.wwwRoot = args[0];
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
