# 🧪 Require4Testing

> Webbasierte Anwendung zur strukturierten Verwaltung manueller Softwaretests

---

## 📌 Projektübersicht

**Require4Testing** ist eine prototypische Webanwendung zur Abbildung eines vollständigen Testprozesses im Softwaretesting.

Die Anwendung ermöglicht die Verwaltung von:

- Anforderungen (Requirements)  
- Testfällen (TestCases)  
- Testläufen (TestRuns)  
- Testdurchführungen (Executions)  

Ziel ist eine durchgängige Nachvollziehbarkeit von Anforderungen bis zur Testausführung und Auswertung.

---

## 🎯 Ziel des Projekts

Das System bildet den gesamten Testprozess ab:

Requirement → TestCase → TestRun → Execution

Zusätzlich werden Testergebnisse im Dashboard ausgewertet.

---

## 🚀 Funktionen

### ✔ Requirements
- Erstellung und Verwaltung von Anforderungen  
- Priorisierung (MUST / SHOULD / COULD)

### ✔ TestCases
- Erstellung von Testfällen mit Beschreibung und Expected Result  
- Zuordnung zu Anforderungen  

### ✔ TestRuns
- Planung von Testläufen  
- Mehrfachzuordnung von Testfällen (n:m Beziehung)

### ✔ Executions
- Durchführung von Tests  
- Verknüpfung von TestCase, TestRun und Tester  
- Speicherung von Ergebnissen (PASSED, FAILED, BLOCKED)

### ✔ Dashboard
- Übersicht über:
  - Gesamtanzahl Tests  
  - Erfolgsquote  
  - Passed / Failed  
- Anzeige der letzten Testausführungen  

---

## 🏗️ Architektur

Die Anwendung basiert auf einer mehrschichtigen Architektur:

- Controller Layer (Verarbeitung von Benutzeranfragen)  
- Service Layer (Geschäftslogik und Validierung)  
- Repository Layer (Datenbankzugriff mit JPA)  

Zusätzlich verwendete Patterns:

- DTO Pattern  
- Mapper Pattern  
- Service Layer Pattern  
- Exception Handling  

---

## 🧠 Datenmodell

Zentrale Entitäten:

- Requirement  
- TestCase  
- TestRun  
- Execution  
- Tester  

### Beziehungen:

- Requirement → TestCase (1:n)  
- TestCase ↔ TestRun (n:m)  
- Execution verbindet TestCase, TestRun und Tester  
- Tester → Execution (1:n)  

---

## 🛠️ Technologien

- Java  
- Spring Boot  
- Spring Data JPA / Hibernate  
- MySQL  
- Thymeleaf  
- Maven  

---

## ⚙️ Installation & Start

### Voraussetzungen:
- Java 11+
- Maven
- MySQL

### 1. Repository klonen

git clone https://github.com/furkanbalkan1616/require4testing.git

### 2. Datenbank erstellen

CREATE DATABASE require4testing_db;

### 3. Konfiguration

application.properties anpassen:

spring.datasource.url=jdbc:mysql://localhost:3306/require4testing_db  
spring.datasource.username=USERNAME  
spring.datasource.password=PASSWORD  

### 4. Anwendung starten

mvn spring-boot:run

### 5. Zugriff

http://localhost:8080

---

## 📸 Screenshots

Die wichtigsten Funktionen sind in der Dokumentation enthalten.  

---

## ⚠️ Einschränkungen

- Kein Login- oder Rollensystem  
- Keine automatisierten Tests  
- Fokus auf prototypische Umsetzung  

---

## 🔮 Weiterentwicklung

- Benutzer- und Rollenverwaltung  
- Erweiterte Testauswertungen  
- REST API  
- Modernes UI  

---

## 👨‍💻 Autor

Furkan Balkan  
Wirtschaftsinformatik – IU Internationale Hochschule  

---

## 📄 Hinweis

Dieses Projekt wurde im Rahmen einer Studienleistung entwickelt und dient Demonstrationszwecken.
