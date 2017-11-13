Björn Scheppler, 13.11.2017

# Read Me gwr
Dieses Maven-Projekt basierend auf Camunda Spring Boot Starter simuliert das 
Umsystem Gebäude- und Wohnungsregister, welches Operationen u.a. für die
eUmzugsplattform bereitstellt

# Status
- Testdaten für Wohnungen und Gebäude integriert
- Webservice-Operationen funktionsfähig implementiert

# Todo
- Dokumentation aller Klassen abschliessen
- Momentan falscher Namespace ns2 bei Antworten (könnte man leicht anpassen mit
  package.info, aber dann kann man es natürlich nicht mehr im target generieren lassen.
  Die umständliche Version wäre mit einer HandlerChain zu arbeiten)
- Serializable musste händisch als Implementation zu Gebaeude- und Wohnung-Entity 
  hinzugefügt werden, damit List<Wohnung> als Objekt einer Prozessvariable hinzugefügt
  werden kann. Kann das auch in Jeddict konfiguriert werden?