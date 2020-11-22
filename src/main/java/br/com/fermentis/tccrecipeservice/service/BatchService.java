package br.com.fermentis.tccrecipeservice.service;

import br.com.fermentis.tccrecipeservice.model.dto.BatchDTO;
import br.com.fermentis.tccrecipeservice.model.dto.ControlProfileDTO;
import br.com.fermentis.tccrecipeservice.model.dto.RecipeDTO;
import br.com.fermentis.tccrecipeservice.model.entity.Batch;
import br.com.fermentis.tccrecipeservice.model.entity.ControlProfile;
import br.com.fermentis.tccrecipeservice.model.entity.Recipe;
import br.com.fermentis.tccrecipeservice.model.enumerator.BatchStatus;
import br.com.fermentis.tccrecipeservice.model.repository.BatchRepository;
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
public class BatchService {

    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private ControlProfileService controlProfileService;

    public Page<BatchDTO> getBatchesByRecipe (Long recipeId, String query, Pageable pageable) {
        query = "%" + query + "%";
        Page<Batch> batches = batchRepository.getByRecipe(recipeId, query, pageable);
        return new PageImpl<>(batches.getContent().stream().map(batch -> new BatchDTO(batch, new ControlProfileDTO(batch.getControlProfile()))).collect(toList()), pageable, batches.getTotalElements());
    }

    public BatchDTO createBatch(BatchDTO batchDTO) throws Exception {
        // TODO: Validate object (fields and duplicate)
        Recipe recipe = recipeService.findRecipe(batchDTO.getRecipe().getId()).orElseThrow(() -> new Exception("Recipe not found"));
        ControlProfile controlProfile = controlProfileService.mapFrom(controlProfileService.createControlProfile(batchDTO.getControlProfile()));
        Batch batch = mapFrom(batchDTO, recipe, controlProfile);
        batchRepository.save(batch);
        return new BatchDTO(batch, new ControlProfileDTO(controlProfile));
    }

    private Batch mapFrom(BatchDTO batchDTO, Recipe recipe, ControlProfile controlProfile) {
        return Batch.builder()
                .id(batchDTO.getId())
                .name(batchDTO.getName())
                .misc(batchDTO.getMisc())
                .recipe(recipe)
                .status(BatchStatus.STAND_BY)
                .controlProfile(controlProfile)
                // TODO: get User
                .createdBy(1L)
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
    }

    @Transactional(readOnly = true)
    protected Optional<Batch> findBatch(Long batchId) {
        return batchRepository.findById(batchId);
    }

    public BatchDTO getBatch(long batchId) throws Exception {
        Batch batch = findBatch(batchId).orElseThrow(() -> new Exception("Batch not found"));
        ControlProfileDTO controlProfileDTO = controlProfileService.getControlProfile(batch.getControlProfile().getId());
        return new BatchDTO(batch, controlProfileDTO);
    }

    @Transactional
    public BatchDTO updateBatch(Long batchId, BatchDTO batchDTO) throws Exception {
        ControlProfileDTO controlProfileDTO = controlProfileService.updateControlProfile(batchDTO.getControlProfile().getId(), batchDTO.getControlProfile());
        Batch batch = findBatch(batchId).orElseThrow(() -> new Exception("Batch not found"));
        // TODO: Validate object (fields and duplicate)
        batch.setName(batchDTO.getName());
        batch.setMisc(batchDTO.getMisc());
        batch.setUpdatedAt(new Date());
        batchRepository.save(batch);
        return new BatchDTO(batch, controlProfileDTO);
    }

    @Transactional
    public void deleteBatch(Long batchId) throws Exception {
        Batch batch = findBatch(batchId).orElseThrow(() -> new Exception("Batch not found"));
        batch.setDeletedAt(new Date());
        batch.setUpdatedAt(new Date());
        batchRepository.save(batch);
    }
}
