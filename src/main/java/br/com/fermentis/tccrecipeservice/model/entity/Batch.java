package br.com.fermentis.tccrecipeservice.model.entity;

import br.com.fermentis.tccrecipeservice.model.enumerator.BatchStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(schema = "recipe", name = "batches")
public class Batch {
    @Id
    @Column(name = "batch_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @Column(name = "started_at")
    private Date startedAt;

    @Column(name = "finished_at")
    private Date finishedAt;

    @Column(name = "status")
//    private BatchStatus status;
    private String status;
    //TODO BatchStatus

    @Column(name = "misc")
    private String misc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "control_profile_id")
    private ControlProfile controlProfile;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "deleted_at")
    private Date deletedAt;
}
