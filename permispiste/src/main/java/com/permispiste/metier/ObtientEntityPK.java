package com.permispiste.metier;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Robin on 12/06/2017.
 */
public class ObtientEntityPK implements Serializable {
    private int numapprenant;
    private Date datejour;
    private int numaction;

    @Column(name = "NUMAPPRENANT", nullable = false)
    @Id
    public int getNumapprenant() {
        return numapprenant;
    }

    public void setNumapprenant(int numapprenant) {
        this.numapprenant = numapprenant;
    }

    @Column(name = "DATEJOUR", nullable = false)
    @Id
    public Date getDatejour() {
        return datejour;
    }

    public void setDatejour(Date datejour) {
        this.datejour = datejour;
    }

    @Column(name = "NUMACTION", nullable = false)
    @Id
    public int getNumaction() {
        return numaction;
    }

    public void setNumaction(int numaction) {
        this.numaction = numaction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ObtientEntityPK that = (ObtientEntityPK) o;

        if (numapprenant != that.numapprenant) return false;
        if (numaction != that.numaction) return false;
        if (datejour != null ? !datejour.equals(that.datejour) : that.datejour != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numapprenant;
        result = 31 * result + (datejour != null ? datejour.hashCode() : 0);
        result = 31 * result + numaction;
        return result;
    }
}
