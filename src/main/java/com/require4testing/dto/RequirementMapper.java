package com.require4testing.dto;

import com.require4testing.entity.Requirement;

public class RequirementMapper {

    public static Requirement toEntity(RequirementDTO dto) {
        if (dto == null) return null;

        Requirement r = new Requirement();

        r.setId(dto.getId()); // ✅ KRITISCHER FIX
        r.setTitle(dto.getTitle());
        r.setDescription(dto.getDescription());
        r.setPriority(dto.getPriority());

        return r;
    }

    public static RequirementDTO toDTO(Requirement r) {
        if (r == null) return null;

        RequirementDTO dto = new RequirementDTO();

        dto.setId(r.getId());
        dto.setTitle(r.getTitle());
        dto.setDescription(r.getDescription());
        dto.setPriority(r.getPriority());

        return dto;
    }

    // ✅ BONUS (für Updates)
    public static void updateEntity(Requirement r, RequirementDTO dto) {
        r.setTitle(dto.getTitle());
        r.setDescription(dto.getDescription());
        r.setPriority(dto.getPriority());
    }
}
