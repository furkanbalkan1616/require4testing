package com.require4testing.enums;

public enum Status {

    PLANNED("Geplant", "bg-secondary"),
    IN_PROGRESS("In Bearbeitung", "bg-warning"),
    COMPLETED("Abgeschlossen", "bg-success");

    private final String displayName;
    private final String cssClass;

    Status(String displayName, String cssClass) {
        this.displayName = displayName;
        this.cssClass = cssClass;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getCssClass() {
        return cssClass;
    }
}