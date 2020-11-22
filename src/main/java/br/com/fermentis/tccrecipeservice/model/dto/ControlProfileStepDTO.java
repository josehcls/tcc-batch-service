package br.com.fermentis.tccrecipeservice.model.dto;

import br.com.fermentis.tccrecipeservice.model.entity.ControlProfileStep;
import br.com.fermentis.tccrecipeservice.model.enumerator.ControlVariable;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ControlProfileStepDTO {
    @JsonProperty(value = "control_profile_step_id")
    private Long id;

    @JsonProperty(value = "step")
    private Integer step;

    @JsonProperty(value = "variable")
    private ControlVariable variable;

    @JsonProperty(value = "value")
    private BigDecimal value;

    @JsonProperty(value = "time_offset")
    private Long timeOffset;

    public ControlProfileStepDTO (ControlProfileStep controlProfileStep) {
        this.id = controlProfileStep.getId();
        this.step = controlProfileStep.getStep();
        this.variable = controlProfileStep.getVariable();
        this.value = controlProfileStep.getValue();
        this.timeOffset = controlProfileStep.getTimeOffset();
    }
}
