package ch.zhaw.sml.iwi.gpi.gwr.delegates;

import ch.zhaw.sml.iwi.gpi.gwr.entities.Gebaeude;
import ch.zhaw.sml.iwi.gpi.gwr.repositories.GebaeudeRepository;
import java.util.List;
import javax.inject.Named;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Gebäudesuche mit den Adressangaben
 * Es wird in der Register-Datenbank eine Abfrage auf die Entity Gebaeude platziert, 
 * welche einer AND-Kombination aus allen extrahierten Variablen entspricht. 
 * Als Antwort kommt 0-n Treffer von der Datenbank zurück. Falls ungleich 1 
 * Treffer zurück kommen, wird die Variable exists auf false gesetzt. 
 * Bei einem Treffer wird diese Variable auf true gesetzt und die Variable gebid 
 * erhält den Wert der Spalte GEBID.
 */
@Named("getBuildingFromGwrDbAdapter")
public class GetBuildingFromGwrDbDelegate implements JavaDelegate{
    
    @Autowired
    private GebaeudeRepository gebaeudeRepository;
    
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        // PLZ-Prozessvariable wird lokaler Variable zugewiesen
        Integer dplz4 = (Integer) delegateExecution.getVariable("dplz4");
        
        // Strassennamen-Prozessvariable wird lokaler Variable zugewiesen
        String strname = (String) delegateExecution.getVariable("strname");
        
        // Hausnummer-Prozessvariable wird lokaler Variable zugewiesen
        String deinr = (String) delegateExecution.getVariable("deinr");
        
        // Datenbank-Suche nach der Kombination aus den drei Variablen-Werten durchführen
        List<Gebaeude> gefundeneGebaeude = gebaeudeRepository.findByAddressAttributes(dplz4, strname, deinr);
        
        // Exists-Variable setzen in Abhängigkeit davon, ob ein Gebäude gefunden wurde
        Boolean exists;
        exists = gefundeneGebaeude.size() == 1;
        
        // Die exists-Variable der gleich benannten Prozess-Variable zuweisen
        delegateExecution.setVariable("exists", exists);
        
        // Falls exists wahr ist, auch die Gebäude-Id der Prozess-Variable gebid 
        // zuweisen und fehlerTyp auf 0 setzen
        if(exists){
            delegateExecution.setVariable("gebid", gefundeneGebaeude.get(0).getGEBID());
            delegateExecution.setVariable("fehlerTyp", 0);
        }
    }
}
