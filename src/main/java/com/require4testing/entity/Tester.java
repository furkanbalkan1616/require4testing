package com.require4testing.entity;

import jakarta.persistence.*;

@Entity
public class Tester {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // 🔥 LEERER KONSTRUKTOR (Pflicht für JPA)
    public Tester() {
    }

    // 🔥 KONSTRUKTOR MIT NAME (DEIN FIX)
    public Tester(String name) {
        this.name = name;
    }

    // ===== Getter / Setter =====

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}