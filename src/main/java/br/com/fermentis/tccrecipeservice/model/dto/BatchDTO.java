package br.com.fermentis.tccrecipeservice.model.dto;

import br.com.fermentis.tccrecipeservice.model.entity.Batch;
import br.com.fermentis.tccrecipeservice.model.enumerator.BatchStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BatchDTO {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("recipe")
    private RecipeDTO recipe;

    @JsonProperty("started_at")
    private Date startedAt;

    @JsonProperty("finished_at")
    private Date finishedAt;

    @JsonProperty("status")
//    private BatchStatus status;
    private String status;

    @JsonProperty("misc")
    private String misc;

    @JsonProperty("control_profile")
    private ControlProfileDTO controlProfile;

    @JsonProperty("created_by")
    private Long createdBy;

    @JsonProperty("created_at")
    private Date createdAt;

    public BatchDTO (Batch batch) {
        this.id = batch.getId();
        this.name = batch.getName();
        this.recipe = new RecipeDTO(batch.getRecipe());
        this.startedAt = batch.getStartedAt();
        this.finishedAt = batch.getFinishedAt();
        this.status = batch.getStatus();
        this.misc = batch.getMisc();
        this.controlProfile = null; // TODO Control Profile DTO
        this.createdBy = batch.getCreatedBy();
        this.createdAt = batch.getCreatedAt();
    }
}
