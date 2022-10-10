package ru.grabovsky.bridge;

public class About extends WebPage {

    public About(Theme theme) {
        super(theme);
    }

    @Override
    public String getContent() {
        return "About page in " +theme.getColor();
    }
}
