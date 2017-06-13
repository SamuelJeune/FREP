package com.permispiste.metier;

import javax.persistence.*;

/**
 * Created by Robin on 12/06/2017.
 */
@Entity
@Table(name = "fixe", schema = "permispiste", catalog = "")
@IdClass(FixeEntityPK.class)
public class FixeEntity {
    private int nummission;
    private int numobjectif;
    private MissionEntity missionByNummission;
    private ObjectifEntity objectifByNumobjectif;

    @Id
    @Column(name = "NUMMISSION", nullable = false)
    public int getNummission() {
        return nummission;
    }

    public void setNummission(int nummission) {
        this.nummission = nummission;
    }

    @Id
    @Column(name = "NUMOBJECTIF", nullable = false)
    public int getNumobjectif() {
        return numobjectif;
    }

    public void setNumobjectif(int numobjectif) {
        this.numobjectif = numobjectif;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FixeEntity that = (FixeEntity) o;

        if (nummission != that.nummission) return false;
        if (numobjectif != that.numobjectif) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nummission;
        result = 31 * result + numobjectif;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "NUMMISSION", referencedColumnName = "NUMMISSION", nullable = false)
    public MissionEntity getMissionByNummission() {
        return missionByNummission;
    }

    public void setMissionByNummission(MissionEntity missionByNummission) {
        this.missionByNummission = missionByNummission;
    }

    @ManyToOne
    @JoinColumn(name = "NUMOBJECTIF", referencedColumnName = "NUMOBJECTIF", nullable = false)
    public ObjectifEntity getObjectifByNumobjectif() {
        return objectifByNumobjectif;
    }

    public void setObjectifByNumobjectif(ObjectifEntity objectifByNumobjectif) {
        this.objectifByNumobjectif = objectifByNumobjectif;
    }
}
