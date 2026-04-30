package com.require4testing.dto;

import com.require4testing.entity.TestCase;

public class TestCaseMapper {

    // ===== DTO → ENTITY =====
    public static TestCase toEntity(TestCaseDTO dto) {
        if (dto == null) return null;

        TestCase tc = new TestCase();

        tc.setId(dto.getId()); // ✅ wichtig für Update
        tc.setTitle(dto.getTitle());
        tc.setDescription(dto.getDescription());
        tc.setExpectedResult(dto.getExpectedResult());

        // ❗ requirement wird im Service gesetzt (richtig so!)
        return tc;
    }

    // ===== ENTITY → DTO =====
    public static TestCaseDTO toDTO(TestCase tc) {
        if (tc == null) return null;

        TestCaseDTO dto = new TestCaseDTO();

        dto.setId(tc.getId());
        dto.setTitle(tc.getTitle());
        dto.setDescription(tc.getDescription());
        dto.setExpectedResult(tc.getExpectedResult());

        // 🔗 Beziehung sauber auflösen
        if (tc.getRequirement() != null) {
            dto.setRequirementId(tc.getRequirement().getId());
            dto.setRequirementTitle(tc.getRequirement().getTitle()); // 💥 UI-Bonus
        }

        return dto;
    }

    // ===== UPDATE ENTITY =====
    public static void updateEntity(TestCase tc, TestCaseDTO dto) {

        tc.setTitle(dto.getTitle());
        tc.setDescription(dto.getDescription());
        tc.setExpectedResult(dto.getExpectedResult());

        // ❗ requirement wird im Service aktualisiert
    }
}