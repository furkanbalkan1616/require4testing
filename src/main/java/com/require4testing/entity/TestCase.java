package com.require4testing.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Entity
@Table(name = "testcases")
public class TestCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Titel ist Pflicht")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Beschreibung ist Pflicht") // ✅ FIX (Konsistenz!)
    @Column(nullable = false)
    private String description;

    @NotBlank(message = "Expected Result ist Pflicht")
    @Column(nullable = false)
    private String expectedResult;

    // 🔗 Beziehung zu Requirement
    @ManyToOne(fetch = FetchType.LAZY, optional = false) // ✅ FIX
    @JoinColumn(name = "requirement_id", nullable = false) // ✅ FIX
    private Requirement requirement;

    // ===== KONSTRUKTOREN =====
    public TestCase() {}

    public TestCase(String title, String description, String expectedResult) {
        this.title = title;
        this.description = description;
        this.expectedResult = expectedResult;
    }

    // ===== GETTER =====
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getExpectedResult() { return expectedResult; }
    public Requirement getRequirement() { return requirement; }

    // ===== SETTER =====
    public void setId(Long id) { this.id = id; } // ✅ FIX für Updates

    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setExpectedResult(String expectedResult) { this.expectedResult = expectedResult; }

    public void setRequirement(Requirement requirement) {
        this.requirement = requirement;
    }

    // ===== DOMAIN LOGIC (🔥 PRO LEVEL) =====
    public void assignToRequirement(Requirement requirement) {
        Objects.requireNonNull(requirement, "Requirement darf nicht null sein");

        this.requirement = requirement;
        requirement.addTestCase(this); // hält beide Seiten konsistent
    }

    // ===== EQUALS & HASHCODE =====
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestCase that)) return false;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    // ===== TO STRING =====
    @Override
    public String toString() {
        return "TestCase{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}