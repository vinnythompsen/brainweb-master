package br.com.brainweb.interview.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;
@Entity
@Table(name="power_stats", schema = "interview_service")
public class PowerStats {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID id;
    @NotNull
    private Integer strength;
    @NotNull
    private Integer agility;
    @NotNull
    private Integer dexterity;
    @NotNull
    private Integer intelligence;
    @NotNull
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;
    @NotNull
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated_at;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Integer getAgility() {
        return agility;
    }

    public void setAgility(Integer agility) {
        this.agility = agility;
    }

    public Integer getDexterity() {
        return dexterity;
    }

    public void setDexterity(Integer dexterity) {
        this.dexterity = dexterity;
    }

    public Integer getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(Integer intelligence) {
        this.intelligence = intelligence;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
}
