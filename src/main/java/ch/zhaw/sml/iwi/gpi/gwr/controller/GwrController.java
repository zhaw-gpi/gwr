package ch.zhaw.sml.iwi.gpi.gwr.controller;

import ch.zhaw.iwi.gpi.gwr.AddresseExistenzType;
import ch.zhaw.iwi.gpi.gwr.AdresseType;
import ch.zhaw.iwi.gpi.gwr.FehlerType;
import ch.zhaw.iwi.gpi.gwr.WohnungType;
import ch.zhaw.iwi.gpi.gwr.WohnungenAntwortType;
import ch.zhaw.sml.iwi.gpi.gwr.entities.Wohnung;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.runtime.ProcessInstanceWithVariables;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Implementation für den GwrService
 * Diese Klasse enthält die eigentliche Implementation der Web Service-Operationen,
 * wobei jede Funktion einer gleich lautenden Web Service-Operation entspricht
 */
public class GwrController {
    
    /**
     * Unser Web Service nutzt die Camunda Process Engine als Abhängigkeit
     * Über @Autowired wird die zur Laufzeit verfügbare Process Engine-Instanz
     * in diesem Service als Variable processEngine eingefügt (Dependency Injection)
     */ 
    @Autowired
    private ProcessEngine processEngine;
    
    /**
     * Implementation der Web Service-Operation Adresspruefung
     * Startet eine neue Instanz des Prozesses CheckAddressExistence und übergibt dieser
     * die Adress-Angaben als Variablen. Die Variablen am Ende des Prozesses werden
     * ausgelesen und den entsprechenden Eigenschaften des AdresseExistenzType-Objekts
     * zugewiesen. Dieses wird schliesslich an den Webservice-Endpoint zurück
     * gegeben.
     * 
     * @param address
     * @return 
     */
    public AddresseExistenzType checkAddressExistence(AdresseType address){
        // Map vorbereiten für die Übergabe an die Prozessinstanz als Prozessvariablen
        Map<String, Object> properties = new HashMap<>();
        properties.put("deinr", address.getDEINR());
        properties.put("dplz4", address.getDPLZ4());
        properties.put("strname", address.getSTRNAME());
        
        // Eine neue Instanz des Prozesses mit der Id "CheckAddressExistence" wird gestartet
        // Die vorbereiteten Variablen werden übergeben
        // Als Antwort kommen die zuletzt bekannten Prozessvariablen zurück
        // Da der Prozess hierbei vollständig durchgelaufen ist, kommen also die
        // Variablen zurück, wie sie beim Endereignis eingetroffen sind
        ProcessInstanceWithVariables processInstanceWithVariables = processEngine.getRuntimeService()
                .createProcessInstanceByKey("CheckAddressExistence")
                .setVariables(properties)
                .executeWithVariablesInReturn();
            
        // Die zurück erhaltenen Variablen werden in einer Map gespeichert
        Map<String, Object> processEndVariables = processInstanceWithVariables.getVariables();

        // Ein neues AddresseExistenzType-Objekt wird instanziert
        AddresseExistenzType addresseExistenz = new AddresseExistenzType();
        
        // Die Eigenschaft EXISTS wird auf den entsprechenden Prozessvariablen-Inhalt gesetzt 
        addresseExistenz.setEXISTS((Boolean) processEndVariables.get("exists"));
        
        // Das AddresseExistenzType-Objekt wird zurück gegeben
        return addresseExistenz;
    }
    
    /**
     * Implementation der Web Service-Operation WohnungenAnfrage
     * Startet eine neue Instanz des Prozesses GetDwellingsOfBuilding und übergibt dieser
     * die Adress-Angaben als Variablen. Die Variablen am Ende des Prozesses werden
     * ausgelesen und den entsprechenden Eigenschaften des wohnungenAntwortType-Objekts
     * zugewiesen. Dieses wird schliesslich an den Webservice-Endpoint zurück
     * gegeben.
     * 
     * @param address
     * @return 
     */
    public WohnungenAntwortType getDwellingsOfBuilding(AdresseType address){
        // Map vorbereiten für die Übergabe an die Prozessinstanz als Prozessvariablen
        Map<String, Object> properties = new HashMap<>();
        properties.put("deinr", address.getDEINR());
        properties.put("dplz4", address.getDPLZ4());
        properties.put("strname", address.getSTRNAME());
        
        // Eine neue Instanz des Prozesses mit der Id "GetDwellingsOfBuilding" wird gestartet
        // Die vorbereiteten Variablen werden übergeben
        // Als Antwort kommen die zuletzt bekannten Prozessvariablen zurück
        // Da der Prozess hierbei vollständig durchgelaufen ist, kommen also die
        // Variablen zurück, wie sie beim Endereignis eingetroffen sind
        ProcessInstanceWithVariables processInstanceWithVariables = processEngine.getRuntimeService()
                .createProcessInstanceByKey("GetDwellingsOfBuilding")
                .setVariables(properties)
                .executeWithVariablesInReturn();
            
        // Die zurück erhaltenen Variablen werden in einer Map gespeichert
        Map<String, Object> processEndVariables = processInstanceWithVariables.getVariables();

        // Ein neues AddresseExistenzType-Objekt wird instanziert
        WohnungenAntwortType wohnungenAntwort = new WohnungenAntwortType();
        
        // Die Variable fehlerTyp wird auf den entsprechenden Prozessvariablen-Inhalt gesetzt 
        Integer fehlerTyp = (Integer) processEndVariables.get("fehlerTyp");
        
        // Prüfen, ob ein Fehler aufgetreten ist
        if(fehlerTyp > 0){
            // Ein neues Fehlerobjekt erstellen
            FehlerType fehler = new FehlerType();
            
            // FehlerTyp und FehlerText auf die entsprechenden Prozessvariablen-Inhalte gesetzt
            fehler.setFehlerTyp(fehlerTyp);
            fehler.setFehlerText((String) processEndVariables.get("fehlerTypText"));

            // Die Fehlervariable der Antwort hinzufügen
            wohnungenAntwort.setFehler(fehler);
        } else {
            // Wohnungen-Variable auf die entsprechende Prozessvariable setzen
            List<Wohnung> wohnungen = (List<Wohnung>) processEndVariables.get("wohnungen");
            
            // Jede Wohnung in wohnungen der wohnungenAntwort hinzufügen
            for(Wohnung wohnungEntity : wohnungen){
                // Für jedes Wohnungs-Entity-Objekt ein WohnungType-Objekt erzeugen
                WohnungType wohnungType = new WohnungType();
                wohnungType.setWAZIM(wohnungEntity.getWAZIM());
                wohnungType.setWBEZ(wohnungEntity.getWBEZ());
                wohnungType.setWHGNR(wohnungEntity.getWHGNR());
                wohnungType.setWMEHRG(wohnungEntity.getWMEHRG());
                wohnungType.setWSTWK(wohnungEntity.getWSTWK());
                
                // Das WohnungType-Objekt der Wohnungen-Liste in wohnungAntwort hinzufügen
                wohnungenAntwort.getWohnungen().add(wohnungType);
            }
        }
        
        // Das WohnungenAntwortType-Objekt wird zurück gegeben
        return wohnungenAntwort;
    }
}
