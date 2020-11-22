package br.com.fermentis.tccrecipeservice.model.dto;


import br.com.fermentis.tccrecipeservice.model.entity.Recipe;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDTO {
    @JsonProperty("recipe_id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("style")
    private String style;

    @JsonProperty("misc")
    private String misc;

    @JsonProperty("created_by")
    private Long createdBy;

    @JsonProperty("created_at")
    private Date createdAt;

    public RecipeDTO (Recipe recipe) {
        this.id = recipe.getId();
        this.name = recipe.getName();
        this.style = recipe.getStyle();
        this.misc = recipe.getMisc();
        this.createdBy = recipe.getCreatedBy();
        this.createdAt = recipe.getCreatedAt();
    }
}
