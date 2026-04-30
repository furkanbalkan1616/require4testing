package com.require4testing.repository;

import com.require4testing.entity.TestCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestCaseRepository extends JpaRepository<TestCase, Long> {

    // ===== BASIC QUERIES =====

    // Alle TestCases zu einem Requirement (über ID)
    List<TestCase> findByRequirement_Id(Long requirementId);

    // Suche nach Titel (für UI / Filter)
    List<TestCase> findByTitleContainingIgnoreCase(String keyword);

    // ===== VALIDIERUNG =====

    // Existenzprüfung (z. B. Duplikate vermeiden)
    boolean existsByTitle(String title);

    // ===== ADVANCED (1,0 BONUS) =====

    // Pagination für große Datenmengen
    Page<TestCase> findByRequirement_Id(Long requirementId, Pageable pageable);

    // Kombination: Requirement + Suche
    List<TestCase> findByRequirement_IdAndTitleContainingIgnoreCase(Long requirementId, String keyword);
}