package ru.grabovsky.bridge;

public class Projects extends WebPage {

    public Projects(Theme theme) {
        super(theme);
    }

    @Override
    public String getContent() {
        return "Projects page in " +theme.getColor();
    }
}
