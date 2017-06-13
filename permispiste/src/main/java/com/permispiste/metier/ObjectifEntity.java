package com.permispiste.metier;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Robin on 12/06/2017.
 */
@Entity
@Table(name = "objectif", schema = "permispiste2", catalog = "")
public class ObjectifEntity {
    private int numobjectif;
    private String libobectif;
    private Collection<EstAssocieEntity> estAssociesByNumobjectif;
    private Collection<FixeEntity> fixesByNumobjectif;

    @Id
    @Column(name = "NUMOBJECTIF", nullable = false)
    public int getNumobjectif() {
        return numobjectif;
    }

    public void setNumobjectif(int numobjectif) {
        this.numobjectif = numobjectif;
    }

    @Basic
    @Column(name = "LIBOBECTIF", nullable = true, length = 25)
    public String getLibobectif() {
        return libobectif;
    }

    public void setLibobectif(String libobectif) {
        this.libobectif = libobectif;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ObjectifEntity that = (ObjectifEntity) o;

        if (numobjectif != that.numobjectif) return false;
        if (libobectif != null ? !libobectif.equals(that.libobectif) : that.libobectif != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numobjectif;
        result = 31 * result + (libobectif != null ? libobectif.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "objectifByNumobjectif")
    public Collection<EstAssocieEntity> getEstAssociesByNumobjectif() {
        return estAssociesByNumobjectif;
    }

    public void setEstAssociesByNumobjectif(Collection<EstAssocieEntity> estAssociesByNumobjectif) {
        this.estAssociesByNumobjectif = estAssociesByNumobjectif;
    }

    @OneToMany(mappedBy = "objectifByNumobjectif")
    public Collection<FixeEntity> getFixesByNumobjectif() {
        return fixesByNumobjectif;
    }

    public void setFixesByNumobjectif(Collection<FixeEntity> fixesByNumobjectif) {
        this.fixesByNumobjectif = fixesByNumobjectif;
    }
}
