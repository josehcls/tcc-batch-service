package br.com.fermentis.tccrecipeservice.service;

import br.com.fermentis.tccrecipeservice.model.dto.RecipeDTO;
import br.com.fermentis.tccrecipeservice.model.entity.Recipe;
import br.com.fermentis.tccrecipeservice.model.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Transactional(readOnly = true)
    public Page<RecipeDTO> getRecipes(String query, Pageable pageable) {
        query = "%" + query + "%";
        Page<Recipe> recipes = recipeRepository.getRecipes(query, pageable);
        return new PageImpl<>(recipes.getContent().stream().map(RecipeDTO::new).collect(toList()), pageable, recipes.getTotalElements());
    }

    protected Optional<Recipe> findRecipe(Long recipeId) {
        return recipeRepository.findById(recipeId);
    }

    @Transactional(readOnly = true)
    public RecipeDTO getRecipe(long recipeId) throws Exception {
        Recipe recipe = findRecipe(recipeId).orElseThrow(() -> new Exception("Recipe not found"));
        return new RecipeDTO(recipe);
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
                .id(recipeDTO.getId())
                .name(recipeDTO.getName())
                .style(recipeDTO.getStyle())
                .misc(recipeDTO.getMisc())
                // TODO: get User
                .createdBy(1L)
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
    }

    @Transactional
    public RecipeDTO updateRecipe(Long recipeId, RecipeDTO recipeDTO) throws Exception {
        Recipe recipe = findRecipe(recipeId).orElseThrow(() -> new Exception("Recipe not found"));
        // TODO: Validate object (fields and duplicate)
        recipe.setName(recipeDTO.getName());
        recipe.setStyle(recipeDTO.getStyle());
        recipe.setMisc(recipeDTO.getMisc());
        recipe.setUpdatedAt(new Date());
        recipeRepository.save(recipe);
        return new RecipeDTO(recipe);
    }

    @Transactional
    public void deleteRecipe(Long recipeId) throws Exception {
        Recipe recipe = findRecipe(recipeId).orElseThrow(() -> new Exception("Recipe not found"));
        recipe.setDeletedAt(new Date());
        recipe.setUpdatedAt(new Date());
        recipeRepository.save(recipe);
    }
}
