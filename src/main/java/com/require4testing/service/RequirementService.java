package com.require4testing.service;

import com.require4testing.entity.Requirement;
import com.require4testing.repository.RequirementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequirementService {

    private final RequirementRepository repository;

    public RequirementService(RequirementRepository repository) {
        this.repository = repository;
    }

    public Requirement save(Requirement requirement) {
        return repository.save(requirement);
    }

    public List<Requirement> findAll() {
        return repository.findAll();
    }
}
