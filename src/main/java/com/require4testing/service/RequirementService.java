package com.require4testing.service;

import com.require4testing.dto.RequirementDTO;
import com.require4testing.dto.RequirementMapper;
import com.require4testing.entity.Requirement;
import com.require4testing.repository.RequirementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RequirementService {

    private final RequirementRepository repository;

    public RequirementService(RequirementRepository repository) {
        this.repository = repository;
    }

    // ===== CREATE / UPDATE =====
    public RequirementDTO save(RequirementDTO dto) {

        if (dto == null) {
            throw new IllegalArgumentException("RequirementDTO darf nicht null sein");
        }

        // 🔎 UPDATE
        if (dto.getId() != null) {

            Requirement existing = repository.findById(dto.getId())
                    .orElseThrow(() -> new RuntimeException("Requirement nicht gefunden"));

            RequirementMapper.updateEntity(existing, dto);

            return RequirementMapper.toDTO(existing);
        }

        // 🔎 CREATE
        if (repository.existsByTitle(dto.getTitle())) {
            throw new RuntimeException("Requirement mit diesem Titel existiert bereits");
        }

        Requirement entity = RequirementMapper.toEntity(dto);
        Requirement saved = repository.save(entity);

        return RequirementMapper.toDTO(saved);
    }

    // ===== DELETE =====
    public void deleteById(Long id) {

        if (!repository.existsById(id)) {
            throw new RuntimeException("Requirement nicht gefunden");
        }

        repository.deleteById(id);
    }

    // ===== FIND ALL =====
    @Transactional(readOnly = true)
    public List<RequirementDTO> findAllDTOs() {
        return repository.findAll()
                .stream()
                .map(RequirementMapper::toDTO)
                .toList();
    }

    // ===== FIND BY ID =====
    @Transactional(readOnly = true)
    public RequirementDTO findById(Long id) {
        Requirement requirement = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Requirement nicht gefunden"));

        return RequirementMapper.toDTO(requirement);
    }

    // ===== SEARCH =====
    @Transactional(readOnly = true)
    public List<RequirementDTO> search(String keyword) {

        if (keyword == null || keyword.isBlank()) {
            return findAllDTOs();
        }

        return repository.findByTitleContainingIgnoreCase(keyword)
                .stream()
                .map(RequirementMapper::toDTO)
                .toList();
    }

    // ===== COUNT (FIX für deinen Fehler) =====
    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }
}