/**
 * This file was generated by the Jeddict
 */
package ch.zhaw.sml.iwi.gpi.gwr.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author scep
 */
@Entity
public class Gebaeude implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long GEBID;

    @Column(nullable = false)
    @Basic(optional = false)
    @NotNull
    @Min(value = 1000)
    @Max(value = 9999)
    private int DPLZ4;

    @Column(nullable = false)
    @Basic(optional = false)
    @NotNull
    @Size(max = 60)
    private String STRNAME;

    @Column(nullable = false)
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    private String DEINR;

    @OneToMany(cascade = {CascadeType.ALL}, targetEntity = Wohnung.class, orphanRemoval = true, mappedBy = "Gebaeude")
    private List<Wohnung> Wohnungen;

    public Long getGEBID() {
        return this.GEBID;
    }

    public void setGEBID(Long GEBID) {
        this.GEBID = GEBID;
    }

    public int getDPLZ4() {
        return this.DPLZ4;
    }

    public void setDPLZ4(int DPLZ4) {
        this.DPLZ4 = DPLZ4;
    }

    public String getSTRNAME() {
        return this.STRNAME;
    }

    public void setSTRNAME(String STRNAME) {
        this.STRNAME = STRNAME;
    }

    public String getDEINR() {
        return this.DEINR;
    }

    public void setDEINR(String DEINR) {
        this.DEINR = DEINR;
    }

    public List<Wohnung> getWohnungen() {
        if (Wohnungen == null) {
            Wohnungen = new ArrayList<>();
        }
        return this.Wohnungen;
    }

    public void setWohnungen(List<Wohnung> Wohnungen) {
        this.Wohnungen = Wohnungen;
    }

    public void addWohnungen(Wohnung Wohnungen) {
        getWohnungen().add(Wohnungen);
        Wohnungen.setGebaeude(this);
    }

    public void removeWohnungen(Wohnung Wohnungen) {
        getWohnungen().remove(Wohnungen);
        Wohnungen.setGebaeude(null);
    }

}