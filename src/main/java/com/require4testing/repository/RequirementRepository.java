package com.require4testing.repository;

import com.require4testing.entity.Requirement;
import com.require4testing.enums.Priority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RequirementRepository extends JpaRepository<Requirement, Long> {

    // ===== OPTIONAL: BUSINESS LOGIC SUPPORT =====

    // Prüfen ob Titel bereits existiert (für Validierung im Service)
    boolean existsByTitle(String title);

    // Requirement anhand Titel finden
    Optional<Requirement> findByTitle(String title);

    // Nach Priorität filtern (z. B. fürs Dashboard / UI)
    List<Requirement> findByPriority(Priority priority);

    // Suche nach Titel (Teilstring)
    List<Requirement> findByTitleContainingIgnoreCase(String keyword);
}