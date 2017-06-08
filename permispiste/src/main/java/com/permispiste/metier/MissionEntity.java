package com.permispiste.metier;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Robin on 02/06/2017.
 */
@Entity
@Table(name = "mission", schema = "permispiste", catalog = "")
public class MissionEntity {
    private int nummission;
    private String libmission;
    private Collection<FixeEntity> fixesByNummission;
    private JeuEntity jeuByNumjeu;
    private int numjeu;

    @Id
    @Column(name = "NUMMISSION", nullable = false)
    public int getNummission() {
        return nummission;
    }

    public void setNummission(int nummission) {
        this.nummission = nummission;
    }

    @Basic
    @Column(name = "LIBMISSION", nullable = true, length = 25)
    public String getLibmission() {
        return libmission;
    }

    public void setLibmission(String libmission) {
        this.libmission = libmission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MissionEntity that = (MissionEntity) o;

        if (nummission != that.nummission) return false;
        if (libmission != null ? !libmission.equals(that.libmission) : that.libmission != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nummission;
        result = 31 * result + (libmission != null ? libmission.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "missionByNummission")
    public Collection<FixeEntity> getFixesByNummission() {
        return fixesByNummission;
    }

    public void setFixesByNummission(Collection<FixeEntity> fixesByNummission) {
        this.fixesByNummission = fixesByNummission;
    }

    @ManyToOne
    @JoinColumn(name = "NUMJEU", referencedColumnName = "NUMJEU", nullable = false)
    public JeuEntity getJeuByNumjeu() {
        return jeuByNumjeu;
    }

    public void setJeuByNumjeu(JeuEntity jeuByNumjeu) {
        this.jeuByNumjeu = jeuByNumjeu;
    }

    @Basic
    @Column(name = "NUMJEU")
    public int getNumjeu() {
        return numjeu;
    }

    public void setNumjeu(int numjeu) {
        this.numjeu = numjeu;
    }
}
