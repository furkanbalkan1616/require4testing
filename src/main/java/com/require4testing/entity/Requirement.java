package com.require4testing.entity;

import com.require4testing.enums.Priority;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "requirements")
public class Requirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Titel ist Pflicht")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Beschreibung ist Pflicht")
    @Column(nullable = false)
    private String description;

    @NotNull(message = "Priorität wählen")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority;

    @OneToMany(
            mappedBy = "requirement",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY // ✅ FIX
    )
    private List<TestCase> testCases = new ArrayList<>();

    // ===== KONSTRUKTOREN =====
    public Requirement() {}

    public Requirement(String title, String description, Priority priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    // ===== DOMAIN LOGIC =====
    public void addTestCase(TestCase testCase) {
        Objects.requireNonNull(testCase);

        if (!testCases.contains(testCase)) {
            testCases.add(testCase);
            testCase.setRequirement(this);
        }
    }

    public void removeTestCase(TestCase testCase) {
        if (testCases.remove(testCase)) {
            testCase.setRequirement(null);
        }
    }

    // ===== GETTER =====
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public Priority getPriority() { return priority; }

    public List<TestCase> getTestCases() {
        return Collections.unmodifiableList(testCases); // ✅ FIX
    }

    // ===== SETTER =====
    public void setId(Long id) { this.id = id; } // ✅ FIX
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setPriority(Priority priority) { this.priority = priority; }

    // ===== EQUALS & HASHCODE =====
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Requirement that)) return false;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}