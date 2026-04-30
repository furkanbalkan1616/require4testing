package com.require4testing.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TestCaseDTO {

    private Long id;

    @NotBlank(message = "Titel darf nicht leer sein")
    private String title;

    @NotBlank(message = "Beschreibung darf nicht leer sein")
    private String description;

    @NotBlank(message = "Expected Result darf nicht leer sein")
    private String expectedResult;

    @NotNull(message = "Requirement muss gewählt werden")
    private Long requirementId;

    // Nur für Anzeige im UI (nicht zum Speichern!)
    private String requirementTitle;

    // ===== KONSTRUKTOREN (BONUS) =====
    public TestCaseDTO() {}

    public TestCaseDTO(Long id, String title, String description,
                       String expectedResult, Long requirementId, String requirementTitle) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.expectedResult = expectedResult;
        this.requirementId = requirementId;
        this.requirementTitle = requirementTitle;
    }

    // ===== GETTER & SETTER =====

    public Long getId() {
        return id;
    }

    public void setId(Long id) { // wichtig für Update
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }

    public Long getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(Long requirementId) {
        this.requirementId = requirementId;
    }

    public String getRequirementTitle() {
        return requirementTitle;
    }

    public void setRequirementTitle(String requirementTitle) {
        this.requirementTitle = requirementTitle;
    }
}