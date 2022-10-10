package ru.grabovsky.bridge;

public abstract class  WebPage {
    protected Theme theme;

    public WebPage(Theme theme) {
        this.theme = theme;
    }

    public abstract String getContent();
}
