package br.com.fermentis.tccrecipeservice.service;

import br.com.fermentis.tccrecipeservice.model.dto.BatchDTO;
import br.com.fermentis.tccrecipeservice.model.dto.RecipeDTO;
import br.com.fermentis.tccrecipeservice.model.entity.Batch;
import br.com.fermentis.tccrecipeservice.model.entity.Recipe;
import br.com.fermentis.tccrecipeservice.model.repository.BatchRepository;
import br.com.fermentis.tccrecipeservice.model.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class BatchService {

    @Autowired
    private BatchRepository batchRepository;

    public List<BatchDTO> getBatchesByRecipe (Long recipeId) {
        List<Batch> batches = batchRepository.findByRecipe(recipeId);
        return batches.stream().map(BatchDTO::new).collect(toList());
    }
}
