package com.require4testing.repository;

import com.require4testing.entity.Execution;
import com.require4testing.enums.ExecutionResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExecutionRepository extends JpaRepository<Execution, Long> {

    // ===== COUNT BY RESULT =====
    long countByResult(ExecutionResult result);
}