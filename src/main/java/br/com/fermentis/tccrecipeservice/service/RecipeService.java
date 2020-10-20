package br.com.fermentis.tccrecipeservice.service;

import br.com.fermentis.tccrecipeservice.model.dto.RecipeDTO;
import br.com.fermentis.tccrecipeservice.model.entity.Recipe;
import br.com.fermentis.tccrecipeservice.model.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Transactional(readOnly = true)
    public List<RecipeDTO> findRecipes() {
        List<Recipe> recipes = recipeRepository.findAll();
        return recipes.stream().map(RecipeDTO::new).collect(toList());
    }

    @Transactional(readOnly = true)
    public RecipeDTO findRecipe(Long recipeId) {
        Optional<Recipe> recipe = getRecipe(recipeId);
        return recipe.isPresent() ? new RecipeDTO(recipe.get()) : null;
    }

    protected Optional<Recipe> getRecipe(long recipeId) {
        return recipeRepository.findById(recipeId);
    }

    @Transactional
    public RecipeDTO createRecipe(RecipeDTO recipeDTO) {
        // TODO: Validate object (fields and duplicate)
        Recipe recipe = mapFrom(recipeDTO);
        recipeRepository.save(recipe);
        return new RecipeDTO(recipe);
    }

    private Recipe mapFrom(RecipeDTO recipeDTO) {
        return Recipe.builder()
                .name(recipeDTO.getName())
                .style(recipeDTO.getStyle())
                // TODO: get User
                .createdBy(1L)
                .createdAt(new Date())
                .build();
    }
}
