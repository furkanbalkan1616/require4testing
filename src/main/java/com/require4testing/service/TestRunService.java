package com.require4testing.service;

import com.require4testing.entity.TestRun;
import com.require4testing.repository.TestRunRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestRunService {

    private final TestRunRepository repository;

    public TestRunService(TestRunRepository repository) {
        this.repository = repository;
    }

    public TestRun save(TestRun testRun) {
        return repository.save(testRun);
    }

    public List<TestRun> findAll() {
        return repository.findAll();
    }
}
