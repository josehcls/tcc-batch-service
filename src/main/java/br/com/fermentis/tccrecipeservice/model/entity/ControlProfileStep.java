package br.com.fermentis.tccrecipeservice.model.entity;

import br.com.fermentis.tccrecipeservice.model.enumerator.ControlVariable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(schema = "control", name = "control_profile_steps")
public class ControlProfileStep {
    @Id
    @Column(name = "control_profile_step_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "variable")
    private ControlVariable variable;

    @Column(name = "value")
    private BigDecimal value;

    @Column(name = "time_offset")
    private Long timeOffset;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "deleted_at")
    private Date deletedAt;
}
