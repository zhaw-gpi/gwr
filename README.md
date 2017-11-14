Björn Scheppler, 14.11.2017

# Read Me gwr
Dieses Maven-Projekt basierend auf Camunda Spring Boot Starter simuliert das 
Umsystem Gebäude- und Wohnungsregister, welches Operationen u.a. für die
eUmzugsplattform bereitstellt

# Testing mit SoapUI
1. Ein neues SOAP-Projekt anlegen basierend auf der WSDL http://localhost:8090/soap/GebaeudeUndWohnungsRegisterService?wsdl
2. Es hat zwei Operationen, wobei beide als Input-Parameter eine Adresse benötigen, wobei
   DEINR=Hausnummer, DPLZ4=PLZ und STRNAME=Strasse ist.
3. Was für Testdaten genutzt werden können, kann über http://localhost:8090/console
   herausgefunden werden durch einen Blick in die Tabelle GEBAEUDE (anmelden mit
   jdbc:h2:./gwr -> sa -> sa). Das Grundprinzip ist einfach:
   1. DEINR=1, STRNAME=Bahnhofstrasse, DPLZ4=irgendeine PLZ im Kanton Bern (z.B. 3000)
      => Adresse wird gefunden, zwei Wohnungen an dieser Adresse
   2. DEINR=13a, STRNAME=Dorfstrasse, DPLZ4=dito => Adresse wird gefunden, aber
      keine Wohnungen an dieser Adresse
   3. DEINR=x, STRNAME=x, PLZ=0, wobei X und 0 als Platzhalter zu verstehen sind
      => keine Adresse gefunden
   4. "Falsche Anfrage", z.B. "AdresspruefungAnfrag" statt "AdresspruefungAnfrage"
      oder ein Buchstabe bei DPLZ4 => Soap:Fault-Meldung kommt (finde ich ok)
   5. "Falsche Anfrage" aus Sicht von XSD-Restrictions (z.B. min-length nicht
      eingehalten) => Erzeugt keinen Fehler, sondern einfach, dass keine Adresse
      gefunden wird => müssen wir noch in den Griff kriegen (siehe Todo)

# Todo
- Validation (min-length & Co.) einbauen
- Momentan falscher Namespace ns2 bei Antworten (Die umständliche Version wäre 
  mit einer HandlerChain zu arbeiten)