package com.require4testing.service;

import com.require4testing.dto.TestRunDTO;
import com.require4testing.dto.TestRunMapper;
import com.require4testing.entity.TestCase;
import com.require4testing.entity.TestRun;
import com.require4testing.exception.NotFoundException;
import com.require4testing.repository.TestCaseRepository;
import com.require4testing.repository.TestRunRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TestRunService {

    private final TestRunRepository repository;
    private final TestCaseRepository testCaseRepository;

    public TestRunService(TestRunRepository repository,
                          TestCaseRepository testCaseRepository) {
        this.repository = repository;
        this.testCaseRepository = testCaseRepository;
    }

    // ===== SAVE (CREATE + UPDATE) =====
    public TestRunDTO save(TestRunDTO dto) {

        if (dto == null) {
            throw new IllegalArgumentException("DTO darf nicht null sein");
        }

        TestRun tr;

        // UPDATE
        if (dto.getId() != null) {

            tr = repository.findById(dto.getId())
                    .orElseThrow(() -> new NotFoundException("TestRun nicht gefunden"));

            TestRunMapper.updateEntity(tr, dto);

        } else {
            tr = TestRunMapper.toEntity(dto);
        }

        // 🔥 TestCases setzen (WICHTIG!)
        tr.getTestCases().clear();

        if (dto.getTestCaseIds() != null) {
            List<TestCase> testCases = testCaseRepository.findAllById(dto.getTestCaseIds());
            testCases.forEach(tr::addTestCase);
        }

        TestRun saved = repository.save(tr);

        return TestRunMapper.toDTO(saved);
    }

    // ===== FIND ALL =====
    @Transactional(readOnly = true)
    public List<TestRunDTO> findAllDTOs() {
        return repository.findAll()
                .stream()
                .map(TestRunMapper::toDTO)
                .toList();
    }

    // ===== DELETE =====
    public void deleteById(Long id) {

        if (!repository.existsById(id)) {
            throw new NotFoundException("TestRun nicht gefunden");
        }

        repository.deleteById(id);
    }

    // ===== COUNT =====
    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }
}