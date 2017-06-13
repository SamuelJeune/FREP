package com.permispiste.metier;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Robin on 12/06/2017.
 */
@Entity
@Table(name = "apprenant", schema = "permispiste2", catalog = "")
public class ApprenantEntity {
    private int numapprenant;
    private String nomapprenant;
    private String prenomapprenant;
    private Collection<InscritEntity> inscritsByNumapprenant;
    private Collection<ObtientEntity> obtientsByNumapprenant;

    @Id
    @Column(name = "NUMAPPRENANT", nullable = false)
    public int getNumapprenant() {
        return numapprenant;
    }

    public void setNumapprenant(int numapprenant) {
        this.numapprenant = numapprenant;
    }

    @Basic
    @Column(name = "NOMAPPRENANT", nullable = true, length = 25)
    public String getNomapprenant() {
        return nomapprenant;
    }

    public void setNomapprenant(String nomapprenant) {
        this.nomapprenant = nomapprenant;
    }

    @Basic
    @Column(name = "PRENOMAPPRENANT", nullable = true, length = 25)
    public String getPrenomapprenant() {
        return prenomapprenant;
    }

    public void setPrenomapprenant(String prenomapprenant) {
        this.prenomapprenant = prenomapprenant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApprenantEntity that = (ApprenantEntity) o;

        if (numapprenant != that.numapprenant) return false;
        if (nomapprenant != null ? !nomapprenant.equals(that.nomapprenant) : that.nomapprenant != null) return false;
        if (prenomapprenant != null ? !prenomapprenant.equals(that.prenomapprenant) : that.prenomapprenant != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numapprenant;
        result = 31 * result + (nomapprenant != null ? nomapprenant.hashCode() : 0);
        result = 31 * result + (prenomapprenant != null ? prenomapprenant.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "apprenantByNumapprenant")
    public Collection<InscritEntity> getInscritsByNumapprenant() {
        return inscritsByNumapprenant;
    }

    public void setInscritsByNumapprenant(Collection<InscritEntity> inscritsByNumapprenant) {
        this.inscritsByNumapprenant = inscritsByNumapprenant;
    }

    @OneToMany(mappedBy = "apprenantByNumapprenant")
    public Collection<ObtientEntity> getObtientsByNumapprenant() {
        return obtientsByNumapprenant;
    }

    public void setObtientsByNumapprenant(Collection<ObtientEntity> obtientsByNumapprenant) {
        this.obtientsByNumapprenant = obtientsByNumapprenant;
    }
}
