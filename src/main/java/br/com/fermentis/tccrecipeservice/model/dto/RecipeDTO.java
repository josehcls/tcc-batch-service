package br.com.fermentis.tccrecipeservice.model.dto;


import br.com.fermentis.tccrecipeservice.model.entity.Recipe;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RecipeDTO {
    @JsonProperty("recipe_id")
    private Long recipeId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("style")
    private String style;

    @JsonProperty("created_by")
    private Long createdBy;

    @JsonProperty("created_at")
    private Date createdAt;

    public RecipeDTO (Recipe recipe) {
        this.recipeId = recipe.getId();
        this.name = recipe.getName();
        this.style = recipe.getStyle();
        this.createdBy = recipe.getCreatedBy();
        this.createdAt = recipe.getCreatedAt();
    }
}
