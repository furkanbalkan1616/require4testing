# 🧪 Require4Testing

Require4Testing ist eine webbasierte Anwendung zur strukturierten Verwaltung von Anforderungen, Testfällen und Testläufen im Kontext des Softwaretestens.

## 📌 Projektbeschreibung

Require4Testing ist eine prototypische Anwendung, die im Rahmen einer Fallstudie entwickelt wurde. Ziel ist es, den gesamten Testprozess – von der Definition einer Anforderung bis zur Durchführung und Auswertung von Testläufen – systematisch abzubilden.

Die Anwendung unterstützt eine klare Nachvollziehbarkeit von Anforderungen und deren Validierung durch Testfälle und Testausführungen.

## 🚀 Funktionen

- Verwaltung von Anforderungen (Requirements)  
- Erstellung und Zuordnung von Testfällen (TestCases)  
- Durchführung von Testläufen (TestRuns)  
- Dokumentation von Testergebnissen (Executions)  
- Übersicht über den aktuellen Teststatus (Dashboard)  

## 🏗️ Architektur

Die Anwendung basiert auf einer mehrschichtigen Architektur (Layered Architecture):

- Controller-Schicht zur Verarbeitung von Benutzeranfragen  
- Service-Schicht zur Umsetzung der Geschäftslogik  
- Persistenzschicht (Repository) für den Datenbankzugriff  

Zusätzlich werden Data Transfer Objects (DTOs) und Mapper verwendet, um eine klare Trennung zwischen interner Datenstruktur und Präsentationslogik zu gewährleisten.

## 🗄️ Datenmodell

Zentrale Entitäten der Anwendung:

- Requirement  
- TestCase  
- TestRun  
- Execution  
- Tester  

Der Testprozess wird wie folgt abgebildet:

Requirement → TestCase → TestRun → Execution

## 🛠️ Technologien

- Java / Spring Boot  
- Spring Data JPA / Hibernate  
- MySQL  
- Thymeleaf  
- Maven  

## ⚙️ Installation & Ausführung

Repository klonen:  
git clone https://github.com/furkanbalkan1616/require4testing.git  

Projekt öffnen und Anwendung starten:  
mvn spring-boot:run  

Die Anwendung ist anschließend unter folgender Adresse erreichbar:  
http://localhost:8080  

## ⚠️ Einschränkungen

- Kein Authentifizierungs- oder Rollensystem  
- Keine automatisierten Tests  
- Prototypische Umsetzung ohne Fokus auf Skalierbarkeit  

## 🔮 Weiterentwicklung

- Implementierung eines Login-Systems  
- Einführung von Rollen und Berechtigungen  
- Erweiterung um automatisierte Tests  
- Verbesserung der Benutzeroberfläche  

## 👨‍💻 Autor

Furkan Balkan  
Projekt im Rahmen einer IU-Fallstudie  

## 📄 Hinweis

Dieses Projekt dient ausschließlich zu Lern- und Demonstrationszwecken.
