package br.com.fermentis.tccrecipeservice.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(schema = "control", name = "control_profiles")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ControlProfile {
    @Id
    @Column(name = "control_profile_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "control_profile_id")
    private List<ControlProfileStep> steps;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "deleted_at")
    private Date deletedAt;
}
