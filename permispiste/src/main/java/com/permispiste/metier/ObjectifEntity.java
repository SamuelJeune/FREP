package com.permispiste.metier;

import javax.persistence.*;

@Entity
@Table(name = "objectif", schema = "permispiste", catalog = "")
public class ObjectifEntity {
    private int numobjectif;
    private String libobjectif;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NUMOBJECTIF")
    public int getNumobjectif() {
        return numobjectif;
    }

    public void setNumobjectif(int numobjectif) {
        this.numobjectif = numobjectif;
    }

    @Basic
    @Column(name = "LIBOBECTIF")
    public String getLibobjectif() {
        return libobjectif;
    }

    public void setLibobjectif(String libobjectif) {
        this.libobjectif = libobjectif;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ObjectifEntity that = (ObjectifEntity) o;

        if (numobjectif != that.numobjectif) return false;
        if (libobjectif != null ? !libobjectif.equals(that.libobjectif) : that.libobjectif != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numobjectif;
        result = 31 * result + (libobjectif != null ? libobjectif.hashCode() : 0);
        return result;
    }
}
