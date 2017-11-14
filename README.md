Björn Scheppler, 14.11.2017

# Read Me gwr
Dieses Maven-Projekt basierend auf Camunda Spring Boot Starter simuliert das 
Umsystem Gebäude- und Wohnungsregister, welches Operationen u.a. für die
eUmzugsplattform bereitstellt

# Testfälle soapUI
## Vorbedingungen für alle Testfälle
1. soapUI in der Version 5.3.0 ist gestartet
2. Das soapUI-Testsuite-Projekt src/test/resources/GebaeudeUndWohnungsRegisterServiceTests-
   soapui-project.xml ist importiert mittels der Import-Schaltfläche
3. Der Testsuite-Editor ist geöffnet (Linksklick auf GebaeudeUndWohnungsRegisterService 
   TestSuite -> EINGABE-Taste drücken)

## Gesamttest über TestSuite
### Testsequenz
Auf das grüne Dreieck-Symbol im TestSuite-Editor-Fenster klicken

### Erwartetes Ergebnis
Alle Balken im Testsuite-Editor sind grün und enthalten den Text "FINISHED"

## Beschreibung der in der TestSuite enthaltenen TestCases und TestSteps
1. Die einzelnen Teststeps in der TestSuite sind mit selbsterklärenden Namen bezeichnet
2. Ein Doppelklick auf eine TestStep-Bezeichnung öffnet eine Maske, wo der
   SOAP-Request und die erhaltene Response aufgeführt sind.
3. Ein Klick auf Assertions öffnet die zugehörigen erwarteten Ergebnisse, welche
   sich auf die Response beziehen.
4. Ein Doppelklick auf eine Assertion öffnet die detaillierte Konfiguration

# Todo
- Validation (min-length & Co.) einbauen
- Momentan falscher Namespace ns2 bei Antworten (Die umständliche Version wäre 
  mit einer HandlerChain zu arbeiten)