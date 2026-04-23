package com.require4testing.controller;

import com.require4testing.entity.TestRun;
import com.require4testing.service.TestRunService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TestRunController {

    private final TestRunService service;

    public TestRunController(TestRunService service) {
        this.service = service;
    }

    @GetMapping("/testruns")
    public String getTestRuns(Model model) {
        model.addAttribute("testruns", service.findAll());
        model.addAttribute("testrun", new TestRun());
        return "testruns";
    }

    @PostMapping("/testruns")
    public String saveTestRun(@ModelAttribute TestRun testRun) {
        service.save(testRun);
        return "redirect:/testruns";
    }
}
