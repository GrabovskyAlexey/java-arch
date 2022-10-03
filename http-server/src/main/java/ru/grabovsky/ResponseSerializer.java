package ru.grabovsky;

import ru.grabovsky.domain.HttpResponse;

public interface ResponseSerializer {

    String serialize(HttpResponse httpResponse);
}
