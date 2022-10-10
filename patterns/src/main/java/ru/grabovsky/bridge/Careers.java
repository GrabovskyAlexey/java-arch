package ru.grabovsky.bridge;

public class Careers extends WebPage {

    public Careers(Theme theme) {
        super(theme);
    }

    @Override
    public String getContent() {
        return "Careers page in " +theme.getColor();
    }
}
