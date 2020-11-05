package br.com.fermentis.tccrecipeservice.controller.v1;

import br.com.fermentis.tccrecipeservice.model.dto.BatchDTO;
import br.com.fermentis.tccrecipeservice.model.dto.RecipeDTO;
import br.com.fermentis.tccrecipeservice.service.BatchService;
import br.com.fermentis.tccrecipeservice.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<Page<RecipeDTO>> getRecipes(Pageable pageable){
        return ResponseEntity.ok(recipeService.findRecipes(pageable));
    }

    @GetMapping("/{recipeId}")
    public ResponseEntity<RecipeDTO> getRecipe(@PathVariable Long recipeId) throws Exception {
        return ResponseEntity.ok(recipeService.getRecipe(recipeId));
    }

    @PostMapping()
    public ResponseEntity<RecipeDTO> createRecipe(@RequestBody RecipeDTO recipeDTO) {
        return ResponseEntity.ok(recipeService.createRecipe(recipeDTO));
    }

    @PutMapping("/{recipeId}")
    public ResponseEntity<RecipeDTO> updateRecipe(@PathVariable Long recipeId, @RequestBody RecipeDTO recipeDTO) throws Exception {
        return ResponseEntity.ok(recipeService.updateRecipe(recipeId, recipeDTO));
    }

    @DeleteMapping("/{recipeId}")
    public ResponseEntity getRecipes(@PathVariable Long recipeId) throws Exception {
        recipeService.deleteRecipe(recipeId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{recipeId}/batches")
    public ResponseEntity<List<BatchDTO>> getBatchesFromRecipe(@PathVariable Long recipeId) {
        return ResponseEntity.ok(batchService.getBatchesByRecipe(recipeId));
    }

    @PostMapping("/{recipeId}/batches")
    public ResponseEntity<BatchDTO> createBatch (@PathVariable Long recipeId, @RequestBody BatchDTO batchDTO) throws Exception {
        return ResponseEntity.ok(batchService.createBatch(recipeId, batchDTO));
    }
}
