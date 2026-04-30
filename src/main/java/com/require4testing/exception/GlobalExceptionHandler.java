package com.require4testing.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // ===== 404 Fehler =====
    @ExceptionHandler(NotFoundException.class)
    public String handleNotFound(NotFoundException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        model.addAttribute("status", 404);
        return "error";
    }

    // ===== 400 Fehler =====
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleBadRequest(IllegalArgumentException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        model.addAttribute("status", 400);
        return "error";
    }

    // ===== Allgemeiner Fehler =====
    @ExceptionHandler(Exception.class)
    public String handleGeneral(Exception ex, Model model) {
        model.addAttribute("error", "Ein unerwarteter Fehler ist aufgetreten");
        model.addAttribute("status", 500);
        return "error";
    }
}
