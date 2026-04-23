package com.require4testing.service;

import com.require4testing.entity.TestExecution;
import com.require4testing.repository.TestExecutionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestExecutionService {

    private final TestExecutionRepository repository;

    public TestExecutionService(TestExecutionRepository repository) {
        this.repository = repository;
    }

    public TestExecution save(TestExecution execution) {
        return repository.save(execution);
    }

    public List<TestExecution> findAll() {
        return repository.findAll();
    }
}
