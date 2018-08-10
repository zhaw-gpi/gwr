Björn Scheppler, 10.08.2018

# Gebaeude- und Wohnungsregister (GebauedeUndWohnungsRegister)
Dieses Maven-Projekt basierend auf Camunda Spring Boot Starter simuliert das 
Umsystem Gebäude- und Wohnungsregister, welches Operationen u.a. für die
eUmzugsplattform bereitstellt.

Enthalten sind folgende Komponenten/Funktionalitäten:
1. Spring Boot 2.0.2 konfiguriert für Tomcat
2. Camunda Spring Boot Starter 3.0.0
3. Camunda Process Engine, REST API und Webapps (Tasklist, Cockpit, Admin) in der Version 7.9
4. H2-Datenbank-Unterstützung (von Camunda Engine benötigt)
5. SOAP-Webservice-Komponenten (Java API for XML Web Services-Komponenten,  CXF-Servlet-Komponenten)
6. "Sinnvolle" Grundkonfiguration in application.properties für Camunda, Datenbank und Tomcat
7. Prozessmodell (GwrProcesses.bpmn) mit zwei Prozessen:
    1. Adressgültigkeit prüfen
    2. Wohnungen eines Gebäudes ausgeben
8. Camunda Java Delegate-Klassen für die Service Tasks der beiden Prozesse:
    1. Adresse im Register suchen (GetBuildingFromGwrDbDelegate)
    2. Wohnungen an der Adresse auslesen (GetDwellingsOfBuildingDelegate)
9. Datenlayer:
    1. Datenmodell (JPA-Diagramm GwrModel.jpa)
    2. zugehörige Entity-Klassen Gebaeude (BuildingEntity) und Wohnung (DwellingEntity)
    3. CRUD-Repository-Klasse GwrRepository mit zwei Methoden für die Java Delegates,
welcher auf die H2-Datenbank zugreift gemäss application.properties
10. SOAP Web Service-Layer:
    1. WebServiceConfiguration-Klasse
    2. Schnittstellen-Definition (Endpoint: GwrServiceEndpoint)
    3. Implementation des Web Services (GwrController), welche einen der zwei Prozesse
anstösst, damit indirekt die Java Delegates und von da aus den Datenlayer
    4. JAXB-Konfiguration für Wohnungen (global.xjb)
    5. XML-Schema Definition als Basis für das WSDL (gwr.xsd)
11. Test-Fälle als soapUI-Projekt (GwrServiceTests-soapui-project.xml)
12. initialData.sql (und Basis davon als initialData.xlsx) mit INSERT-Statements für die Datenbank (GEBAEUDE und WOHNUNG-Tabellen)

## Vorbereitungen, Deployment und Start
1. Erstmalig oder bei Problemen ein Clean & Build (Netbeans), respektive "mvn clean install" (Cmd) durchführen. Dabei werden im Target nebst den kompilierten Java-Klassen auch
die aus dem gwr.xsd abgeleiteten Java-Klassen generiert vom JAXB2-Maven-Plugin.
2. Bei Änderungen am POM-File oder bei (Neu)kompilierungsbedarf genügt ein Build (Netbeans), respektive "mvn install"
3. Falls man aus verschiedensten Gründen mal wieder eine frische Datenbank will, dann gwr.mv.db löschen
4. Für den Start ist ein Run (Netbeans), respektive "java -jar .\target\GebauedeUndWohnungsRegister-3.0.1.jar" (Cmd) erforderlich. Dabei wird Tomcat wird gestartet, die Datenbank erstellt/hochgefahren, Camunda in der Version 7.9 mit den beiden Prozessen und den Eigenschaften (application.properties) hochgefahren.
5. Die Datenbank gwr.mv.db ist ebenfalls im Github-Projekt enthalten, so dass auch tatsächlich Gebäude und Wohnungen gefunden werden können. Falls man in Schritt 3 die Datenbank gelöscht hat, kann man sich an der H2-Console anmelden (Details siehe unten) und die INSERT-Statements aus initialData.sql ausführen.
6. Das Beenden des Projekts geschieht mit Stop Build/Run (Netbeans), respektive CTRL+C (Cmd)

## Informationen für das Testen
### Tests mit soapUI
1. Starten wie im vorherigen Kapitel beschrieben, damit der SOAP WebService läuft und über die URL http://localhost:8090/soap
verfügbar ist
2. Testen über folgende Varianten:
    1. In soapUI händisch neue SOAP-Requests generieren mit der WSDL
http://localhost:8090/soap/GebaeudeUndWohnungsRegisterService?wsdl. Welche Gebäude
und Wohnungen dabei gefunden werden können, kann über die H2-Konsole geprüft werden 
in den Tabellen GEBAEUDE und WOHNUNG. Hierzu auf http://localhost:8090/console anmelden 
mit Driver Class = org.h2.Driver, JDBC URL = jdbc:h2:./gwr, User Name 
= sa, Password = Leer lassen
    2. Die vorgefertigten Requests/TestSuite nutzen im soapUI-Projekt \src\test\resources\
GebaeudeUndWohnungsRegisterServiceTests-soapui-project.xml

### Tests aus der Umzugsplattform heraus
Hierzu den Anweisungen folgen in https://github.com/zhaw-gpi/eumzug_musterloesung

## Zugriff auf Camunda Webapps
Wohl selten erforderlich, aber falls doch: Anmelden mit Benutzername und Passwort a bei http://localhost:8083

## Offene Punkte
- Validation (min-length & Co.) einbauen
- Momentan falscher Namespace ns2 bei Antworten (Die umständliche Version wäre 
  mit einer HandlerChain zu arbeiten)

## Mitwirkende
1. Björn Scheppler: Hauptarbeit
2. Peter Heinrich: Der stille Support im Hintergrund mit vielen Tipps sowie zuständig
für den Haupt-Stack mit SpringBoot & Co.