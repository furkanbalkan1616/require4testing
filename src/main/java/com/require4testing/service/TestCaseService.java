package com.require4testing.service;

import com.require4testing.entity.TestCase;
import com.require4testing.repository.TestCaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestCaseService {

    private final TestCaseRepository repository;

    public TestCaseService(TestCaseRepository repository) {
        this.repository = repository;
    }

    public TestCase save(TestCase testCase) {
        return repository.save(testCase);
    }

    public List<TestCase> findAll() {
        return repository.findAll();
    }
}
