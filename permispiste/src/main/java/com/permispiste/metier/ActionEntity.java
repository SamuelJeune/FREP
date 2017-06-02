package com.permispiste.metier;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Robin on 02/06/2017.
 */
@Entity
@Table(name = "action", schema = "permispiste", catalog = "")
public class ActionEntity {
    private int numaction;
    private String libaction;
    private Integer scoremin;
    private ActionEntity actionByActNumaction;
    private Collection<ActionEntity> actionsByNumaction;
    private Collection<AppartientEntity> appartientsByNumaction;
    private Collection<EstAssocieEntity> estAssociesByNumaction;
    private Collection<IndicateurEntity> indicateursByNumaction;
    private Collection<ObtientEntity> obtientsByNumaction;

    @Id
    @Column(name = "NUMACTION", nullable = false)
    public int getNumaction() {
        return numaction;
    }

    public void setNumaction(int numaction) {
        this.numaction = numaction;
    }

    @Basic
    @Column(name = "LIBACTION", nullable = true, length = 25)
    public String getLibaction() {
        return libaction;
    }

    public void setLibaction(String libaction) {
        this.libaction = libaction;
    }

    @Basic
    @Column(name = "SCOREMIN", nullable = true)
    public Integer getScoremin() {
        return scoremin;
    }

    public void setScoremin(Integer scoremin) {
        this.scoremin = scoremin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActionEntity that = (ActionEntity) o;

        if (numaction != that.numaction) return false;
        if (libaction != null ? !libaction.equals(that.libaction) : that.libaction != null) return false;
        if (scoremin != null ? !scoremin.equals(that.scoremin) : that.scoremin != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numaction;
        result = 31 * result + (libaction != null ? libaction.hashCode() : 0);
        result = 31 * result + (scoremin != null ? scoremin.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "ACT_NUMACTION", referencedColumnName = "NUMACTION")
    public ActionEntity getActionByActNumaction() {
        return actionByActNumaction;
    }

    public void setActionByActNumaction(ActionEntity actionByActNumaction) {
        this.actionByActNumaction = actionByActNumaction;
    }

    @OneToMany(mappedBy = "actionByActNumaction")
    public Collection<ActionEntity> getActionsByNumaction() {
        return actionsByNumaction;
    }

    public void setActionsByNumaction(Collection<ActionEntity> actionsByNumaction) {
        this.actionsByNumaction = actionsByNumaction;
    }

    @OneToMany(mappedBy = "actionByNumaction")
    public Collection<AppartientEntity> getAppartientsByNumaction() {
        return appartientsByNumaction;
    }

    public void setAppartientsByNumaction(Collection<AppartientEntity> appartientsByNumaction) {
        this.appartientsByNumaction = appartientsByNumaction;
    }

    @OneToMany(mappedBy = "actionByNumaction")
    public Collection<EstAssocieEntity> getEstAssociesByNumaction() {
        return estAssociesByNumaction;
    }

    public void setEstAssociesByNumaction(Collection<EstAssocieEntity> estAssociesByNumaction) {
        this.estAssociesByNumaction = estAssociesByNumaction;
    }

    @OneToMany(mappedBy = "actionByNumaction")
    public Collection<IndicateurEntity> getIndicateursByNumaction() {
        return indicateursByNumaction;
    }

    public void setIndicateursByNumaction(Collection<IndicateurEntity> indicateursByNumaction) {
        this.indicateursByNumaction = indicateursByNumaction;
    }

    @OneToMany(mappedBy = "actionByNumaction")
    public Collection<ObtientEntity> getObtientsByNumaction() {
        return obtientsByNumaction;
    }

    public void setObtientsByNumaction(Collection<ObtientEntity> obtientsByNumaction) {
        this.obtientsByNumaction = obtientsByNumaction;
    }
}
