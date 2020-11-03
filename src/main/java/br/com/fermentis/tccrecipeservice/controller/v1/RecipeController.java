package br.com.fermentis.tccrecipeservice.controller.v1;

import br.com.fermentis.tccrecipeservice.model.dto.BatchDTO;
import br.com.fermentis.tccrecipeservice.model.dto.RecipeDTO;
import br.com.fermentis.tccrecipeservice.service.BatchService;
import br.com.fermentis.tccrecipeservice.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private BatchService batchService;

    @GetMapping()
    public ResponseEntity<List<RecipeDTO>> getRecipes(){
        return ResponseEntity.ok(recipeService.findRecipes());
    }

    @GetMapping("/{recipeId}")
    public ResponseEntity<RecipeDTO> getRecipes(@PathVariable Long recipeId){
        return ResponseEntity.ok(recipeService.findRecipe(recipeId));
    }

    @PostMapping()
    public ResponseEntity<RecipeDTO> createRecipes(@RequestBody RecipeDTO recipeDTO) {
        return ResponseEntity.ok(recipeService.createRecipe(recipeDTO));
    }

    @GetMapping("/{recipeId}/batches")
    public ResponseEntity<List<BatchDTO>> getBatchesFromRecipe(@PathVariable Long recipeId) {
        return ResponseEntity.ok(batchService.getBatchesByRecipe(recipeId));
    }

    @PostMapping("/{recipeId}/batches")
    public ResponseEntity<BatchDTO> createBatche (@PathVariable Long recipeId, @RequestBody BatchDTO batchDTO) {
        return ResponseEntity.ok(batchService.createBatch(recipeId, batchDTO));
    }
}
