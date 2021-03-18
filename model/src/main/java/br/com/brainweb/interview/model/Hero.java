package br.com.brainweb.interview.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.rmi.server.UID;
import java.util.Date;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name="hero", schema = "interview_service")
public class Hero {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID id;
    @NotNull
    @Length(max=255)
    private String name;
    @NotNull
    @Length(max=255)
    private String race;
    @NotNull
    @OneToOne()
    private PowerStats power_stats;
    @NotNull
    @ColumnDefault(value = "TRUE")
    private Boolean enabled;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public PowerStats getPower_stats() {
        return power_stats;
    }

    public void setPower_stats(PowerStats power_stats) {
        this.power_stats = power_stats;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
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
