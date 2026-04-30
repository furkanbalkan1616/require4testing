package com.require4testing.enums;

public enum Priority {

    MUST("Muss", "danger"),
    SHOULD("Soll", "warning"),
    COULD("Kann", "secondary");

    private final String displayName;
    private final String cssClass;

    Priority(String displayName, String cssClass) {
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
