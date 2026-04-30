package com.require4testing.dto;

import com.require4testing.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class TestRunDTO {

    private Long id;

    @NotBlank(message = "Name ist Pflicht")
    private String name;

    @NotNull(message = "Datum ist Pflicht")
    private LocalDate date;

    @NotNull(message = "Status ist Pflicht")
    private Status status;

    @NotNull(message = "Mindestens ein TestCase wählen")
    private List<Long> testCaseIds;

    // 👉 nur für Anzeige
    private List<String> testCaseTitles;

    // ===== GETTER / SETTER =====

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Long> getTestCaseIds() {
        return testCaseIds;
    }

    public void setTestCaseIds(List<Long> testCaseIds) {
        this.testCaseIds = testCaseIds;
    }

    public List<String> getTestCaseTitles() {
        return testCaseTitles;
    }

    public void setTestCaseTitles(List<String> testCaseTitles) {
        this.testCaseTitles = testCaseTitles;
    }
}