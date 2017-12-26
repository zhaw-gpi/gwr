Björn Scheppler, 26.12.2017

Dieses Maven-Projekt basierend auf Camunda Spring Boot Starter simuliert das 
Umsystem Gebäude- und Wohnungsregister, welches Operationen u.a. für die
eUmzugsplattform bereitstellt

# Informationen für das Testen
## Tests mit soapUI
1. Clean & Build: Dabei werden im Target nebst den kompilierten Java-Klassen auch
die aus dem gwr.xsd abgeleiteten Java-Klassen generiert vom JAXB2-Maven-Plugin.
2. Run, damit der SOAP WebService läuft und über die URL http://localhost:8090/soap
verfügbar ist
3. Nun entweder in soapUI händisch neue SOAP-Requests generieren mit der WSDL
http://localhost:8090/soap/GebaeudeUndWohnungsRegisterService?wsdl. Welche Gebäude
und Wohnungen dabei gefunden werden können, kann über die H2-Konsole geprüft werden 
in den Tabellen GEBAEUDE und WOHNUNG. Hierzu auf http://localhost:8090/console anmelden 
mit Driver Class = org.h2.Driver, JDBC URL = jdbc:h2:./gwr, User Name 
= sa, Password = Leer lassen
4. Oder die vorgefertigten Requests/TestSuite nutzen im soapUI-Projekt \src\test\resources\
GebaeudeUndWohnungsRegisterServiceTests-soapui-project.xml

## Tests aus der Umzugsplattform heraus
Hierzu den Anweisungen folgen in https://github.com/zhaw-gpi/eumzug_musterloesung

# TODO
- Validation (min-length & Co.) einbauen
- Momentan falscher Namespace ns2 bei Antworten (Die umständliche Version wäre 
  mit einer HandlerChain zu arbeiten)

# Mitwirkende
1. Björn Scheppler: Hauptarbeit
2. Peter Heinrich: Der stille Support im Hintergrund mit vielen Tipps sowie zuständig
für den Haupt-Stack mit SpringBoot & Co.