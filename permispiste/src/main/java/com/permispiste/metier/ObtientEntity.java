package com.permispiste.metier;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Robin on 12/06/2017.
 */
@Entity
@Table(name = "obtient", schema = "permispiste", catalog = "")
@IdClass(ObtientEntityPK.class)
public class ObtientEntity {
    private int numapprenant;
    private Date datejour;
    private int numaction;
    private Integer valeurdebut;
    private Integer valeurfin;
    private ApprenantEntity apprenantByNumapprenant;
    private CalendrierEntity calendrierByDatejour;
    private ActionEntity actionByNumaction;
    private Integer valeur;

    @Id
    @Column(name = "NUMAPPRENANT", nullable = false)
    public int getNumapprenant() {
        return numapprenant;
    }

    public void setNumapprenant(int numapprenant) {
        this.numapprenant = numapprenant;
    }

    @Id
    @Column(name = "DATEJOUR", nullable = false)
    public Date getDatejour() {
        return datejour;
    }

    public void setDatejour(Date datejour) {
        this.datejour = datejour;
    }

    @Id
    @Column(name = "NUMACTION", nullable = false)
    public int getNumaction() {
        return numaction;
    }

    public void setNumaction(int numaction) {
        this.numaction = numaction;
    }

    @Basic
    @Column(name = "VALEURDEBUT", nullable = true)
    public Integer getValeurdebut() {
        return valeurdebut;
    }

    public void setValeurdebut(Integer valeurdebut) {
        this.valeurdebut = valeurdebut;
    }

    @Basic
    @Column(name = "VALEURFIN", nullable = true)
    public Integer getValeurfin() {
        return valeurfin;
    }

    public void setValeurfin(Integer valeurfin) {
        this.valeurfin = valeurfin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ObtientEntity that = (ObtientEntity) o;

        if (numapprenant != that.numapprenant) return false;
        if (numaction != that.numaction) return false;
        if (datejour != null ? !datejour.equals(that.datejour) : that.datejour != null) return false;
        if (valeurdebut != null ? !valeurdebut.equals(that.valeurdebut) : that.valeurdebut != null) return false;
        if (valeurfin != null ? !valeurfin.equals(that.valeurfin) : that.valeurfin != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numapprenant;
        result = 31 * result + (datejour != null ? datejour.hashCode() : 0);
        result = 31 * result + numaction;
        result = 31 * result + (valeurdebut != null ? valeurdebut.hashCode() : 0);
        result = 31 * result + (valeurfin != null ? valeurfin.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "NUMAPPRENANT", referencedColumnName = "NUMAPPRENANT", nullable = false)
    public ApprenantEntity getApprenantByNumapprenant() {
        return apprenantByNumapprenant;
    }

    public void setApprenantByNumapprenant(ApprenantEntity apprenantByNumapprenant) {
        this.apprenantByNumapprenant = apprenantByNumapprenant;
    }

    @ManyToOne
    @JoinColumn(name = "DATEJOUR", referencedColumnName = "DATEJOUR", nullable = false)
    public CalendrierEntity getCalendrierByDatejour() {
        return calendrierByDatejour;
    }

    public void setCalendrierByDatejour(CalendrierEntity calendrierByDatejour) {
        this.calendrierByDatejour = calendrierByDatejour;
    }

    @ManyToOne
    @JoinColumn(name = "NUMACTION", referencedColumnName = "NUMACTION", nullable = false)
    public ActionEntity getActionByNumaction() {
        return actionByNumaction;
    }

    public void setActionByNumaction(ActionEntity actionByNumaction) {
        this.actionByNumaction = actionByNumaction;
    }

    @Basic
    @Column(name = "VALEUR")
    public Integer getValeur() {
        return valeur;
    }

    public void setValeur(Integer valeur) {
        this.valeur = valeur;
    }
}
