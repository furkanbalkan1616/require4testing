package com.require4testing.dto;

import com.require4testing.enums.ExecutionResult;
import jakarta.validation.constraints.NotNull;

public class ExecutionDTO {

    private Long id;

    // ===== RELATIONEN =====

    @NotNull(message = "TestCase muss gewählt werden")
    private Long testCaseId;

    @NotNull(message = "TestRun muss gewählt werden")
    private Long testRunId;

    @NotNull(message = "Tester muss gewählt werden")
    private Long testerId;

    // ===== ERGEBNIS =====

    @NotNull(message = "Ergebnis muss gewählt werden")
    private ExecutionResult result;

    // ===== UI FELDER (für Anzeige) =====

    private String testCaseTitle;
    private String testRunName;
    private String testerName;

    // ===== GETTER & SETTER =====

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(Long testCaseId) {
        this.testCaseId = testCaseId;
    }

    public Long getTestRunId() {
        return testRunId;
    }

    public void setTestRunId(Long testRunId) {
        this.testRunId = testRunId;
    }

    public Long getTesterId() {
        return testerId;
    }

    public void setTesterId(Long testerId) {
        this.testerId = testerId;
    }

    public ExecutionResult getResult() {
        return result;
    }

    public void setResult(ExecutionResult result) {
        this.result = result;
    }

    public String getTestCaseTitle() {
        return testCaseTitle;
    }

    public void setTestCaseTitle(String testCaseTitle) {
        this.testCaseTitle = testCaseTitle;
    }

    public String getTestRunName() {
        return testRunName;
    }

    public void setTestRunName(String testRunName) {
        this.testRunName = testRunName;
    }

    public String getTesterName() {
        return testerName;
    }

    public void setTesterName(String testerName) {
        this.testerName = testerName;
    }
}
