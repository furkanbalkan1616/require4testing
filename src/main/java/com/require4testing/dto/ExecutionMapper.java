package com.require4testing.dto;

import com.require4testing.entity.Execution;

public class ExecutionMapper {

    // ===== ENTITY → DTO =====
    public static ExecutionDTO toDTO(Execution e) {
        if (e == null) return null;

        ExecutionDTO dto = new ExecutionDTO();

        dto.setId(e.getId());
        dto.setResult(e.getResult());

        // ===== TestCase =====
        if (e.getTestCase() != null) {
            dto.setTestCaseId(e.getTestCase().getId());
            dto.setTestCaseTitle(e.getTestCase().getTitle());
        }

        // ===== TestRun =====
        if (e.getTestRun() != null) {
            dto.setTestRunId(e.getTestRun().getId());
            dto.setTestRunName(e.getTestRun().getName());
        }

        // ===== Tester (🔥 NEU) =====
        if (e.getTester() != null) {
            dto.setTesterId(e.getTester().getId());
            dto.setTesterName(e.getTester().getName());
        }

        return dto;
    }

    // ===== DTO → ENTITY =====
    public static Execution toEntity(ExecutionDTO dto) {
        if (dto == null) return null;

        Execution e = new Execution();
        e.setResult(dto.getResult());

        // Beziehungen werden im Service gesetzt!
        return e;
    }

    // ===== UPDATE =====
    public static void updateEntity(Execution e, ExecutionDTO dto) {
        e.setResult(dto.getResult());
    }
}