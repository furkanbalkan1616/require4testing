package com.require4testing.repository;

import com.require4testing.entity.TestExecution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestExecutionRepository extends JpaRepository<TestExecution, Long> {
}
