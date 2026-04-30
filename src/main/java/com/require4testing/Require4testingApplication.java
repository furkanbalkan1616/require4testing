package com.require4testing;

import com.require4testing.dto.RequirementDTO;
import com.require4testing.dto.TestCaseDTO;
import com.require4testing.entity.Tester;
import com.require4testing.enums.Priority;
import com.require4testing.repository.TesterRepository;
import com.require4testing.service.RequirementService;
import com.require4testing.service.TestCaseService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Require4testingApplication {

    public static void main(String[] args) {
        SpringApplication.run(Require4testingApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(
            RequirementService requirementService,
            TestCaseService testCaseService,
            TesterRepository testerRepository
    ) {
        return args -> {

            // ================================
            // 🔥 REQUIREMENTS + TESTCASES
            // ================================

            if (requirementService.count() == 0) {

                // ===== REQUIREMENT 1 =====
                RequirementDTO r1 = new RequirementDTO();
                r1.setTitle("Login funktioniert");
                r1.setDescription("Benutzer kann sich einloggen");
                r1.setPriority(Priority.MUST);

                RequirementDTO savedR1 = requirementService.save(r1);

                // ===== TESTCASES =====
                TestCaseDTO tc1 = new TestCaseDTO();
                tc1.setTitle("Login mit gültigen Daten");
                tc1.setDescription("Benutzer gibt korrekten Username und Passwort ein");
                tc1.setExpectedResult("Login erfolgreich");
                tc1.setRequirementId(savedR1.getId());
                testCaseService.save(tc1);

                TestCaseDTO tc2 = new TestCaseDTO();
                tc2.setTitle("Login mit falschen Daten");
                tc2.setDescription("Benutzer gibt falsches Passwort ein");
                tc2.setExpectedResult("Fehlermeldung wird angezeigt");
                tc2.setRequirementId(savedR1.getId());
                testCaseService.save(tc2);

                // ===== REQUIREMENT 2 =====
                RequirementDTO r2 = new RequirementDTO();
                r2.setTitle("Registrierung funktioniert");
                r2.setDescription("Benutzer kann sich registrieren");
                r2.setPriority(Priority.SHOULD);

                RequirementDTO savedR2 = requirementService.save(r2);

                // ===== TESTCASE =====
                TestCaseDTO tc3 = new TestCaseDTO();
                tc3.setTitle("Registrierung mit gültigen Daten");
                tc3.setDescription("Benutzer gibt alle Pflichtfelder korrekt ein");
                tc3.setExpectedResult("Registrierung erfolgreich");
                tc3.setRequirementId(savedR2.getId());
                testCaseService.save(tc3);
            }

            // ================================
            // 🔥 TESTER (IMMER WICHTIG!)
            // ================================

            if (testerRepository.count() == 0) {
                testerRepository.save(new Tester("Furkan"));
                testerRepository.save(new Tester("Max"));
                testerRepository.save(new Tester("Anna"));
            }

        };
    }
}