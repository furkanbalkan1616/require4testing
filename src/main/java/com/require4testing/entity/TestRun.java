package com.require4testing.entity;

import com.require4testing.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "testruns")
public class TestRun {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name ist Pflicht")
    @Column(nullable = false)
    private String name;

    // ✅ NEU: Datum
    @NotNull(message = "Datum ist Pflicht")
    @Column(nullable = false)
    private LocalDate date;

    // ✅ NEU: Status
    @NotNull(message = "Status ist Pflicht")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    // 🔗 n:m Beziehung zu TestCases
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "testrun_testcases",
            joinColumns = @JoinColumn(name = "testrun_id"),
            inverseJoinColumns = @JoinColumn(name = "testcase_id")
    )
    private Set<TestCase> testCases = new HashSet<>();

    // ===== KONSTRUKTOREN =====
    public TestRun() {}

    public TestRun(String name, LocalDate date, Status status) {
        this.name = name;
        this.date = date;
        this.status = status;
    }

    // ===== DOMAIN LOGIC =====

    public void addTestCase(TestCase testCase) {
        Objects.requireNonNull(testCase);

        testCases.add(testCase);
    }

    public void removeTestCase(TestCase testCase) {
        testCases.remove(testCase);
    }

    // ===== GETTER =====
    public Long getId() { return id; }
    public String getName() { return name; }
    public LocalDate getDate() { return date; }
    public Status getStatus() { return status; }
    public Set<TestCase> getTestCases() { return testCases; }

    // ===== SETTER =====
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDate(LocalDate date) { this.date = date; }
    public void setStatus(Status status) { this.status = status; }

    // ===== EQUALS & HASHCODE =====
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestRun that)) return false;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}