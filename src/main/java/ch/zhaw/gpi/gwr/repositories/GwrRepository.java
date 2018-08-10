package ch.zhaw.gpi.gwr.repositories;

import ch.zhaw.gpi.gwr.entities.BuildingEntity;
import ch.zhaw.gpi.gwr.entities.DwellingEntity;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Repository mit CRUD- und weitern Operationen für Gebäude- (und Wohnungen-)Objekte
 * Erweitert die mit dem Spring Data JPA-Paket mitgelieferte CrudRepository, so
 * dass einerseits einfache CRUD-Operationen mit der Gebäude-Entität durchgeführt
 * werden können als auch eigene definierte Operationen
 */
public interface GwrRepository extends CrudRepository<BuildingEntity, Long>{
    
    /**
     * Methode, um alle Gebäude an einer Adresse zu erhalten
     * Funktioniert basierend auf einer in JPQL (Java Persistence Query Language)
     * geschriebenen Abfrage
     * @param dplz4
     * @param strname
     * @param deinr
     * @return
     */
    @Query("SELECT g FROM Gebaeude g WHERE g.DPLZ4 = :dplz4 AND g.DEINR = :deinr AND g.STRNAME = :strname")
    public List<BuildingEntity> findByAddressAttributes(@Param("dplz4") Integer dplz4, @Param("strname") String strname, @Param("deinr") String deinr);
    
    /**
     * Methode, um alle Wohnungen eines Gebäudes zu erhalten
     * @param gebaeude
     * @return 
     */
    @Query("SELECT w FROM Wohnung w WHERE w.Gebaeude = :gebaeude")
    public List<DwellingEntity> findDwellingsByGebId(@Param("gebaeude") BuildingEntity gebaeude);
}

