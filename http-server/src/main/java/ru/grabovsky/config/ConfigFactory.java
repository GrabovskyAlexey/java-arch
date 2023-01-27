package ru.grabovsky.config;

public class ConfigFactory {
    public static Config getConfig(String[] args){
        if(args.length == 2){
            return new ConfigFromCli(args);
        } else {
            return new ConfigFromFile("./../../../system.properties");
        }
    }
}
