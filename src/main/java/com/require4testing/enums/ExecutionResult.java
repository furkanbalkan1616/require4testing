package com.require4testing.enums;

public enum ExecutionResult {

    PASSED("Bestanden", "success"),
    FAILED("Fehlgeschlagen", "danger"),
    BLOCKED("Blockiert", "secondary");

    private final String displayName;
    private final String cssClass;

    ExecutionResult(String displayName, String cssClass) {
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