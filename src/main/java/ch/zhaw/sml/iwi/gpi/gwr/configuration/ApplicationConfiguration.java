package ch.zhaw.sml.iwi.gpi.gwr.configuration;

import ch.zhaw.sml.iwi.gpi.gwr.controller.GwrController;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Konfiguriert verschiedene Aspekte der Spring-Application
 * Es werden Beans initialisiert, welche zur Laufzeit benötigt werden
 */
@Configuration
public class ApplicationConfiguration {
    
    /**
     * Initialisierung von GwrController
 Wird benötigt vom ResidentRegisterService und enthält die Implementation
 des gleichnamigen Web Services
     * @return 
     */
    @Bean
    public GwrController residentRegisterController() {
        return new GwrController();
    }
    
//    @Autowired
//    private GebaeudeRepository gebaeudeRepository;
    
    /**
     * TEMP: Datensätze hinzufügen
     * @throws Exception 
     */
    @PostConstruct
    public void addBuildingsAndDwellings() throws Exception{
//        Wohnung wohnung = new Wohnung();
//        
//        wohnung.setWAZIM(3);
//        wohnung.setWBEZ("mitte");
//        wohnung.setWHGNR("123456789ABD");
//        wohnung.setWMEHRG(2);
//        wohnung.setWSTWK(3100);
//        
//        Gebaeude gebaeude = new Gebaeude();
//        
//        gebaeude.setDEINR("14a");
//        gebaeude.setDPLZ4(3104);
//        gebaeude.setSTRNAME("Bodenweg");
//        gebaeude.addWohnungen(wohnung);
//        
//        gebaeudeRepository.save(gebaeude);
    }
    
}
