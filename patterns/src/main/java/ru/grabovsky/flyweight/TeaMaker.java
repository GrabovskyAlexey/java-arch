package ru.grabovsky.flyweight;

import java.util.HashMap;
import java.util.Map;

public class TeaMaker {
    private final Map<String, KarakTea> availableTea = new HashMap<>();

    public KarakTea make(String preference){
            return availableTea.putIfAbsent(preference, new KarakTea());
    }
}
