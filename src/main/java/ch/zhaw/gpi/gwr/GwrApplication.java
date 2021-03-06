package ch.zhaw.gpi.gwr;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hauptklasse für die Prozessapplikation, welche diese mit allen Abhängigkeiten
 * ausführt. Basis ist das SpringBoot-Framework, welches von Camunda erweitert
 * wurde.
 *
 * Zusammengefasst werden dabei folgende Schritte durchlaufen: 0. Abhängigkeiten
 * herunterladen 1. Tomcat initialisieren 2. Camunda REST API aktivieren 3.
 * Camunda Job Executor initialisieren 4. Camunda Process Engine inklusive
 * Datenbank initialisieren gemäss application.properties und application.yaml
 * 5. Sofern noch nicht vorhanden, den Demo-Admin-User erstellen gemäss
 * application.yaml 6. Die Webapps (Tasklist, Admin, Cockpit) deployen 7.
 * Gefundene Prozesse (z.B. ExampleProcess) deployen 8. Alle Komponenten starten
 * => unter localhost:8080 ist Tomcat erreichbar *
 * 
 * @SpringBootApplication deklariert diese Klasse (und die ganze Applikation) als 
 * Spring Boot-Applikation
 * @EnableProcessApplication deklariert diese Klasse (und die ganze Applikation) 
 * als Camunda Prozess-Applikation
 * @ComponentScan durchsucht den angegebenen Namespace nach Konfigurations-Klassen (z.B. 
 * WebService-Configuration), die beim Initialisieren der Spring-Boot-Applikation
 * berücksichtigt werden
 */
@SpringBootApplication
@EnableProcessApplication
@ComponentScan
public class GwrApplication {

    /**
     * Haupt-Methode, welche beim Run-Befehl eine
     * Camunda-Prozessapplikation erstellt.
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(GwrApplication.class, args);
    }
}
