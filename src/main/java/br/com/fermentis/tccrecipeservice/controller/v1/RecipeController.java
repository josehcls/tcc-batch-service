package br.com.fermentis.tccrecipeservice.controller.v1;

import br.com.fermentis.tccrecipeservice.model.dto.BatchDTO;
import br.com.fermentis.tccrecipeservice.model.dto.RecipeDTO;
import br.com.fermentis.tccrecipeservice.service.BatchService;
import br.com.fermentis.tccrecipeservice.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private BatchService batchService;

    @GetMapping()
    public ResponseEntity<List<RecipeDTO>> getRecipes(){
        return ResponseEntity.ok(recipeService.getRecipes());
    }

    @GetMapping("/{recipeId}/batches")
    public ResponseEntity<List<BatchDTO>> getBatchesFromRecipe(@PathVariable Long recipeId) {
        return ResponseEntity.ok(batchService.getBatchesByRecipe(recipeId));
    }
}
