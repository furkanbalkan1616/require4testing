package com.require4testing.service;

import com.require4testing.dto.TestCaseDTO;
import com.require4testing.dto.TestCaseMapper;
import com.require4testing.entity.Requirement;
import com.require4testing.entity.TestCase;
import com.require4testing.exception.NotFoundException;
import com.require4testing.repository.RequirementRepository;
import com.require4testing.repository.TestCaseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TestCaseService {

    private final TestCaseRepository testCaseRepository;
    private final RequirementRepository requirementRepository;

    public TestCaseService(TestCaseRepository testCaseRepository,
                           RequirementRepository requirementRepository) {
        this.testCaseRepository = testCaseRepository;
        this.requirementRepository = requirementRepository;
    }

    // ===== CREATE / UPDATE =====
    public TestCaseDTO save(TestCaseDTO dto) {

        if (dto == null) {
            throw new IllegalArgumentException("TestCaseDTO darf nicht null sein");
        }

        Requirement requirement = requirementRepository.findById(dto.getRequirementId())
                .orElseThrow(() -> new NotFoundException("Requirement nicht gefunden: " + dto.getRequirementId()));

        // 🔎 UPDATE
        if (dto.getId() != null) {

            TestCase existing = testCaseRepository.findById(dto.getId())
                    .orElseThrow(() -> new NotFoundException("TestCase nicht gefunden: " + dto.getId()));

            TestCaseMapper.updateEntity(existing, dto);

            // Beziehung aktualisieren (falls geändert)
            existing.setRequirement(requirement);

            return TestCaseMapper.toDTO(existing);
        }

        // 🔎 CREATE
        TestCase entity = TestCaseMapper.toEntity(dto);
        entity.setRequirement(requirement);

        TestCase saved = testCaseRepository.save(entity);

        return TestCaseMapper.toDTO(saved);
    }

    // ===== FIND ALL =====
    @Transactional(readOnly = true)
    public List<TestCaseDTO> findAllDTOs() {
        return testCaseRepository.findAll()
                .stream()
                .map(TestCaseMapper::toDTO)
                .toList();
    }

    // ===== FIND BY ID =====
    @Transactional(readOnly = true)
    public TestCaseDTO findById(Long id) {
        TestCase testCase = testCaseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("TestCase nicht gefunden: " + id));

        return TestCaseMapper.toDTO(testCase);
    }

    // ===== FIND BY REQUIREMENT =====
    @Transactional(readOnly = true)
    public List<TestCaseDTO> findByRequirement(Long requirementId) {
        return testCaseRepository.findByRequirement_Id(requirementId)
                .stream()
                .map(TestCaseMapper::toDTO)
                .toList();
    }

    // ===== SEARCH =====
    @Transactional(readOnly = true)
    public List<TestCaseDTO> search(String keyword) {

        if (keyword == null || keyword.isBlank()) {
            return findAllDTOs();
        }

        return testCaseRepository.findByTitleContainingIgnoreCase(keyword)
                .stream()
                .map(TestCaseMapper::toDTO)
                .toList();
    }

    // ===== DELETE =====
    public void deleteById(Long id) {

        if (!testCaseRepository.existsById(id)) {
            throw new NotFoundException("TestCase nicht gefunden: " + id);
        }

        testCaseRepository.deleteById(id);
    }

    // ===== COUNT =====
    @Transactional(readOnly = true)
    public long count() {
        return testCaseRepository.count();
    }
}