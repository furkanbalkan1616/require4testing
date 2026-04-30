package com.require4testing.dto;

import com.require4testing.enums.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RequirementDTO {

    private Long id;

    @NotBlank(message = "Titel darf nicht leer sein")
    private String title;

    @NotBlank(message = "Beschreibung darf nicht leer sein") // ✅ FIX
    private String description;

    @NotNull(message = "Priorität muss gewählt werden")
    private Priority priority;

    // ===== GETTER & SETTER =====

    public Long getId() {
        return id;
    }

    public void setId(Long id) { // ✅ wichtig für Updates
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
