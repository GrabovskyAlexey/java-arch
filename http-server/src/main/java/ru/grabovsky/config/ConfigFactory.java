package ru.grabovsky.config;

public class ConfigFactory {
    public static Config getConfig(String[] args){
        // Магическое число
        if(args.length == 2){
            return new ConfigFromCli(args);
        } else {
            // Захардкожен путь к файлу, возможно стоит
            // дать пользователю вариант указать свой файл и
            // только если файл не указан брать файл по умолчанию
            return new ConfigFromFile("./../../../system.properties");
        }
    }
}
