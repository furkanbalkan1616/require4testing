package com.require4testing.controller;

import com.require4testing.entity.TestCase;
import com.require4testing.service.TestCaseService;
import com.require4testing.service.RequirementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TestCaseController {

    private final TestCaseService testCaseService;
    private final RequirementService requirementService;

    public TestCaseController(TestCaseService testCaseService, RequirementService requirementService) {
        this.testCaseService = testCaseService;
        this.requirementService = requirementService;
    }

    @GetMapping("/testcases")
    public String getTestCases(Model model) {
        model.addAttribute("testcases", testCaseService.findAll());
        model.addAttribute("requirements", requirementService.findAll());
        model.addAttribute("testcase", new TestCase());
        return "testcases";
    }

    @PostMapping("/testcases")
    public String saveTestCase(@ModelAttribute TestCase testCase) {
        testCaseService.save(testCase);
        return "redirect:/testcases";
    }
}
