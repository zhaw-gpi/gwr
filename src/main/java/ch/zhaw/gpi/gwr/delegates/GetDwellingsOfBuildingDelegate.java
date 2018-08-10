package ch.zhaw.gpi.gwr.delegates;

import ch.zhaw.gpi.gwr.entities.BuildingEntity;
import ch.zhaw.gpi.gwr.entities.DwellingEntity;
import java.util.List;
import java.util.Optional;
import javax.inject.Named;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import ch.zhaw.gpi.gwr.repositories.GwrRepository;

/**
 * Wohnungen zu einem Gebäude auslesen
 Es wird in der Register-Datenbank eine Abfrage auf die Entity DwellingEntity platziert, 
 welche einer Suche nach dem Fremdschlüssel GEBID = gebid entspricht. Als 
 Antwort kommt 0-n Treffer von der Datenbank zurück. Falls mindestens ein 
 Treffer zurückkommt, wird für jeden Treffer ein DwellingEntity-Objekt gebildet mit 
 den Eigenschaften eCH-0185:administrativDwellingNumber (WHGNR), level (WSTWK), 
 multilevelDwelling (WMEHRG), position (WBEZ) und numberOfRooms (WAZIM). 
 Dieses DwellingEntity-Objekt wird einem Wohnungen-Objekt hinzugefügt.
 * 
 */
@Named("getDwellingsOfBuildingAdapter")
public class GetDwellingsOfBuildingDelegate implements JavaDelegate{
    
    @Autowired
    private GwrRepository gebaeudeRepository;
    
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        // GebäudeId-Prozessvariable wird lokaler Variable zugewiesen
        Long gebid = (Long) delegateExecution.getVariable("gebid");
        
        // Gebäude mit der GebäudeId aus Datenbank lesen
        Optional<BuildingEntity> gebaeudeRepositoryResult = gebaeudeRepository.findById(gebid);
        
        // Wohnungs-Liste-Variable instanzieren
        List<DwellingEntity> gefundeneWohnungen;
        
        // Prüfen, ob wirklich ein Resultat zurückgegeben wurde
        if(gebaeudeRepositoryResult.isPresent()){
            // Falls ja, Datenbank-Suche in der Entität DwellingEntity nach dem Gebäude
            gefundeneWohnungen = gebaeudeRepository.findDwellingsByGebId(gebaeudeRepositoryResult.get());
        } else {
            // Wohnungs-Liste-Variable auf Null setzen
            gefundeneWohnungen = null;
        }
        
        // Die WohnungenListe-Variable der wohnungen-Prozess-Variable zuweisen
        delegateExecution.setVariable("wohnungen", gefundeneWohnungen);
    }
}
