package br.com.fermentis.tccrecipeservice.model.dto;

import br.com.fermentis.tccrecipeservice.model.entity.ControlProfile;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ControlProfileDTO {
    @JsonProperty(value = "control_profile_id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "steps")
    private List<ControlProfileStepDTO> steps;

    @JsonProperty(value = "created_by")
    private Long createdBy;

    @JsonProperty(value = "created_at")
    private Date createdAt;

    public ControlProfileDTO(ControlProfile controlProfile) {
        this.id = controlProfile.getId();
        this.name = controlProfile.getName();
        this.steps = controlProfile.getSteps().stream().map(ControlProfileStepDTO::new).collect(toList());
        this.createdBy = controlProfile.getCreatedBy();
        this.createdAt = controlProfile.getCreatedAt();
    }
}
