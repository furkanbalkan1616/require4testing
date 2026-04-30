package com.require4testing.dto;

import com.require4testing.entity.TestRun;

import java.util.List;
import java.util.stream.Collectors;

public class TestRunMapper {

    // ===== ENTITY → DTO =====
    public static TestRunDTO toDTO(TestRun tr) {
        if (tr == null) return null;

        TestRunDTO dto = new TestRunDTO();

        dto.setId(tr.getId());
        dto.setName(tr.getName());
        dto.setDate(tr.getDate());
        dto.setStatus(tr.getStatus());

        // 🔥 NULL-SAFE
        if (tr.getTestCases() != null) {

            dto.setTestCaseIds(
                    tr.getTestCases().stream()
                            .map(tc -> tc.getId())
                            .collect(Collectors.toList())
            );

            dto.setTestCaseTitles(
                    tr.getTestCases().stream()
                            .map(tc -> tc.getTitle())
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }

    // ===== DTO → ENTITY (für CREATE) =====
    public static TestRun toEntity(TestRunDTO dto) {
        if (dto == null) return null;

        TestRun tr = new TestRun();

        tr.setId(dto.getId());
        tr.setName(dto.getName());
        tr.setDate(dto.getDate());
        tr.setStatus(dto.getStatus());

        // ❗ TestCases werden im Service gesetzt

        return tr;
    }

    // ===== UPDATE ENTITY =====
    public static void updateEntity(TestRun tr, TestRunDTO dto) {

        tr.setName(dto.getName());
        tr.setDate(dto.getDate());
        tr.setStatus(dto.getStatus());

        // ❗ TestCases werden im Service aktualisiert
    }
}