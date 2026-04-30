package com.require4testing.repository;

import com.require4testing.entity.TestRun;
import com.require4testing.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TestRunRepository extends JpaRepository<TestRun, Long> {

    // ===== BASIC FILTER =====

    List<TestRun> findByStatus(Status status);

    List<TestRun> findByDate(LocalDate date);

    List<TestRun> findByNameContainingIgnoreCase(String keyword);

    // ===== ADVANCED (1,0 BONUS) =====

    Page<TestRun> findByStatus(Status status, Pageable pageable);

    List<TestRun> findByStatusAndNameContainingIgnoreCase(Status status, String keyword);

    List<TestRun> findByDateBetween(LocalDate start, LocalDate end);
}
