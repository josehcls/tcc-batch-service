package br.com.fermentis.tccrecipeservice.service;

import br.com.fermentis.tccrecipeservice.model.dto.RecipeDTO;
import br.com.fermentis.tccrecipeservice.model.entity.Recipe;
import br.com.fermentis.tccrecipeservice.model.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public List<RecipeDTO> getRecipes() {
        List<Recipe> recipes = recipeRepository.findAll();
        return recipes.stream().map(RecipeDTO::new).collect(toList());
    }
}
