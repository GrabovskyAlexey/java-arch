package ru.grabovsky;

import ru.grabovsky.domain.HttpRequest;

import java.util.List;

public interface RequestParser {
    HttpRequest parse(List<String> rawRequest);
}
