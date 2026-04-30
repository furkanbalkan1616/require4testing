package com.require4testing.service;

import com.require4testing.dto.ExecutionDTO;
import com.require4testing.dto.ExecutionMapper;
import com.require4testing.entity.Execution;
import com.require4testing.entity.TestCase;
import com.require4testing.entity.TestRun;
import com.require4testing.entity.Tester;
import com.require4testing.enums.ExecutionResult;
import com.require4testing.exception.NotFoundException;
import com.require4testing.repository.ExecutionRepository;
import com.require4testing.repository.TestCaseRepository;
import com.require4testing.repository.TestRunRepository;
import com.require4testing.repository.TesterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ExecutionService {

    private final ExecutionRepository repository;
    private final TestCaseRepository testCaseRepository;
    private final TestRunRepository testRunRepository;
    private final TesterRepository testerRepository;

    public ExecutionService(
            ExecutionRepository repository,
            TestCaseRepository testCaseRepository,
            TestRunRepository testRunRepository,
            TesterRepository testerRepository
    ) {
        this.repository = repository;
        this.testCaseRepository = testCaseRepository;
        this.testRunRepository = testRunRepository;
        this.testerRepository = testerRepository;
    }

    // ===== CREATE / UPDATE =====
    public ExecutionDTO save(ExecutionDTO dto) {

        validate(dto);

        Execution execution = (dto.getId() != null)
                ? repository.findById(dto.getId())
                  .orElseThrow(() -> new NotFoundException("Execution nicht gefunden: " + dto.getId()))
                : ExecutionMapper.toEntity(dto);

        // 🔥 UPDATE nur relevante Felder
        ExecutionMapper.updateEntity(execution, dto);

        // ===== RELATIONEN =====
        execution.setTestCase(findTestCase(dto.getTestCaseId()));
        execution.setTestRun(findTestRun(dto.getTestRunId()));
        execution.setTester(findTester(dto.getTesterId()));

        return ExecutionMapper.toDTO(repository.save(execution));
    }

    // ===== VALIDATION =====
    private void validate(ExecutionDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("ExecutionDTO darf nicht null sein");
        }

        if (dto.getTestCaseId() == null) {
            throw new IllegalArgumentException("TestCase muss gewählt werden");
        }

        if (dto.getTestRunId() == null) {
            throw new IllegalArgumentException("TestRun muss gewählt werden");
        }

        if (dto.getTesterId() == null) {
            throw new IllegalArgumentException("Tester muss gewählt werden");
        }

        if (dto.getResult() == null) {
            throw new IllegalArgumentException("Ergebnis muss gewählt werden");
        }
    }

    // ===== FIND HELPERS =====
    private TestCase findTestCase(Long id) {
        return testCaseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("TestCase nicht gefunden: " + id));
    }

    private TestRun findTestRun(Long id) {
        return testRunRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("TestRun nicht gefunden: " + id));
    }

    private Tester findTester(Long id) {
        return testerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tester nicht gefunden: " + id));
    }

    // ===== FIND ALL =====
    @Transactional(readOnly = true)
    public List<ExecutionDTO> findAllDTOs() {
        return repository.findAll()
                .stream()
                .map(ExecutionMapper::toDTO)
                .toList();
    }

    // ===== DELETE =====
    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Execution nicht gefunden: " + id);
        }
        repository.deleteById(id);
    }

    // ===== STATS =====
    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }

    @Transactional(readOnly = true)
    public long countPassed() {
        return repository.countByResult(ExecutionResult.PASSED);
    }

    @Transactional(readOnly = true)
    public long countFailed() {
        return repository.countByResult(ExecutionResult.FAILED);
    }
}