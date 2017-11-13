package ch.zhaw.sml.iwi.gpi.gwr.endpoint;

import ch.zhaw.iwi.gwr.does_not_exist.*;
import ch.zhaw.sml.iwi.gpi.gwr.controller.GwrController;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Endpoint-Definition
 * Hier wird der eigentliche Web Service definert, was zur Laufzeit im
 * Hintergrund dazu führt, dass ein WSDL generiert wird und dass die Web service-
 * Operationen als Methoden bereit gestellt werden. Werden diese Operationen
 * aufgerufen, wird die jeweilige Methode aufgerufen, welche allerdings nur die
 * eigentliche Implementation aufruft, die in einem separaten Controller stattfindet
 * Die @WebService-JAX-WS-Annotation definiert diese Klasse als Web Service-Klasse
 */
@WebService
public class GebaeudeUndWohnungsregisterService {
    
    /**
     * Die eigentliche Implementation soll getrennt von der Schnittstellen- 
     * Definition sein. Dies wird mit einer separaten Controller-Klasse gelöst.
     * Über @Autowired wird das zur Laufzeit verfügbare Controller-Objekt
     * in diesem Service als Variable eingefügt (Dependency Injection). PS: Damit
     * es zur Laufzeit verfügbar ist, wird es in ApplicationConfiguration.java erstellt
     */
    @Autowired
    private GwrController gwrController;

    /**
     * Definition der Webservice-Operation Adresspruefung
     * Diese erwartet drei Adressparameter und gibt die Existenz als 
     * AddresseExistenzType zurück
     * Die @WebMethod-JAX-WS-Annotation definiert eine Webservice-Operation
     * @param address
     * @WebParam definiert die Bezeichnung der Nachrichten-Input-Parameter
     * @return 
     */
    @WebMethod()
    public AddresseExistenzType adresspruefung(@WebParam(name = "AdresspruefungAnfrage") AdresseType address) {
        // Ruft die Methode checkAddressExistence des Controllers auf und
        // gibt deren Resultat zurück an den SOAP-Client
        return gwrController.checkAddressExistence(address);
    }
    
    /**
     * Definition der Webservice-Operation WohnungenAnfrage
     * Diese erwartet drei Adressparameter und gibt entweder einen Fehler oder 
     * eine Liste von Wohnungen zurück
     * Die @WebMethod-JAX-WS-Annotation definiert eine Webservice-Operation
     * @param address
     * @WebParam definiert die Bezeichnung der Nachrichten-Input-Parameter
     * @return 
     */
    @WebMethod()
    public WohnungenAntwortType wohnungenAnfrage(@WebParam(name = "WohnungenAnfrage") AdresseType address) {
        // Ruft die Methode getDwellingsOfBuilding des Controllers auf und
        // gibt deren Resultat zurück an den SOAP-Client
        return gwrController.getDwellingsOfBuilding(address);
    }

}
