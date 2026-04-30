package com.require4testing.entity;

import com.require4testing.enums.ExecutionResult;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "executions")
public class Execution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExecutionResult result;

    private String comment;

    @Column(nullable = false)
    private LocalDateTime executedAt;

    // ===== RELATIONSHIPS =====

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "testcase_id", nullable = false)
    private TestCase testCase;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "testrun_id", nullable = false)
    private TestRun testRun;

    // 🔥 NEU: TESTER
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tester_id", nullable = false)
    private Tester tester;

    // ===== KONSTRUKTOREN =====

    public Execution() {}

    public Execution(ExecutionResult result, String comment) {
        this.result = result;
        this.comment = comment;
    }

    // ===== LIFECYCLE =====

    @PrePersist
    public void prePersist() {
        if (this.executedAt == null) {
            this.executedAt = LocalDateTime.now();
        }
    }

    // ===== DOMAIN LOGIC =====

    public void assignTo(TestRun testRun, TestCase testCase, Tester tester) {
        this.testRun = Objects.requireNonNull(testRun);
        this.testCase = Objects.requireNonNull(testCase);
        this.tester = Objects.requireNonNull(tester);
    }

    // ===== GETTER =====

    public Long getId() { return id; }
    public ExecutionResult getResult() { return result; }
    public String getComment() { return comment; }
    public LocalDateTime getExecutedAt() { return executedAt; }
    public TestCase getTestCase() { return testCase; }
    public TestRun getTestRun() { return testRun; }
    public Tester getTester() { return tester; } // 🔥 NEU

    // ===== SETTER =====

    public void setResult(ExecutionResult result) { this.result = result; }
    public void setComment(String comment) { this.comment = comment; }
    public void setExecutedAt(LocalDateTime executedAt) { this.executedAt = executedAt; }
    public void setTestCase(TestCase testCase) { this.testCase = testCase; }
    public void setTestRun(TestRun testRun) { this.testRun = testRun; }
    public void setTester(Tester tester) { this.tester = tester; } // 🔥 NEU

    // ===== EQUALS & HASHCODE =====

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Execution that)) return false;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    // ===== TO STRING =====

    @Override
    public String toString() {
        return "Execution{" +
                "id=" + id +
                ", result=" + result +
                ", executedAt=" + executedAt +
                '}';
    }
}