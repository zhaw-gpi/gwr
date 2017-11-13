package ch.zhaw.sml.iwi.gpi.gwr.delegates;

import ch.zhaw.sml.iwi.gpi.gwr.entities.Gebaeude;
import ch.zhaw.sml.iwi.gpi.gwr.entities.Wohnung;
import ch.zhaw.sml.iwi.gpi.gwr.repositories.GebaeudeRepository;
import java.util.List;
import javax.inject.Named;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Wohnungen zu einem Gebäude auslesen
 * Es wird in der Register-Datenbank eine Abfrage auf die Entity Wohnung platziert, 
 * welche einer Suche nach dem Fremdschlüssel GEBID = gebid entspricht. Als 
 * Antwort kommt 0-n Treffer von der Datenbank zurück. Falls mindestens ein 
 * Treffer zurückkommt, wird für jeden Treffer ein Wohnung-Objekt gebildet mit 
 * den Eigenschaften eCH-0185:administrativDwellingNumber (WHGNR), level (WSTWK), 
 * multilevelDwelling (WMEHRG), position (WBEZ) und numberOfRooms (WAZIM). 
 * Dieses Wohnung-Objekt wird einem Wohnungen-Objekt hinzugefügt.
 * 
 */
@Named("getDwellingsOfBuildingAdapter")
public class getDwellingsOfBuildingDelegate implements JavaDelegate{
    
    @Autowired
    private GebaeudeRepository gebaeudeRepository;
    
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        // GebäudeId-Prozessvariable wird lokaler Variable zugewiesen
        Long gebid = (Long) delegateExecution.getVariable("gebid");
        
        // Gebäude mit der GebäudeId aus Datenbank lesen
        Gebaeude gebaeude = gebaeudeRepository.findOne(gebid);
        
        // Datenbank-Suche in der Entität Wohnung nach dem Gebäude
        List<Wohnung> gefundeneWohnungen = gebaeudeRepository.findDwellingsByGebId(gebaeude);
        
        // Die WohnungenListe-Variable der wohnungen-Prozess-Variable zuweisen
        delegateExecution.setVariable("wohnungen", gefundeneWohnungen);
    }
}
