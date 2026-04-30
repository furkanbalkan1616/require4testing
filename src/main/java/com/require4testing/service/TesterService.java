package com.require4testing.service;

import com.require4testing.entity.Tester;
import com.require4testing.repository.TesterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TesterService {

    private final TesterRepository repository;

    public TesterService(TesterRepository repository) {
        this.repository = repository;
    }

    public List<Tester> findAll() {
        return repository.findAll();
    }
}
