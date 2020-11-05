package br.com.fermentis.tccrecipeservice.service;

import br.com.fermentis.tccrecipeservice.model.dto.BatchDTO;
import br.com.fermentis.tccrecipeservice.model.entity.Batch;
import br.com.fermentis.tccrecipeservice.model.entity.ControlProfile;
import br.com.fermentis.tccrecipeservice.model.entity.Recipe;
import br.com.fermentis.tccrecipeservice.model.repository.BatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class BatchService {

    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private ControlProfileService controlProfileService;

    public List<BatchDTO> getBatchesByRecipe (Long recipeId) {
        List<Batch> batches = batchRepository.findByRecipe(recipeId);
        return batches.stream().map(BatchDTO::new).collect(toList());
    }

    public BatchDTO createBatch(Long recipeId, BatchDTO batchDTO) throws Exception {
        // TODO: Validate object (fields and duplicate)
        Recipe recipe = recipeService.findRecipe(recipeId).orElseThrow(() -> new Exception("Recipe not found"));
        ControlProfile controlProfile = controlProfileService.getControlProfile(batchDTO.getControlProfile().getId()).orElse(null);
        Batch batch = mapFrom(batchDTO, recipe, controlProfile);
        batchRepository.save(batch);
        return new BatchDTO(batch);
    }

    private Batch mapFrom(BatchDTO batchDTO, Recipe recipe, ControlProfile controlProfile) {
        return Batch.builder()
                .name(batchDTO.getName())
                .misc(batchDTO.getMisc())
                .recipe(recipe)
                .status("CREATED")
                .controlProfile(controlProfile)
                // TODO: get User
                .createdBy(1L)
                .createdAt(new Date())
                .build();
    }
}
