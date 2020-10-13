package br.com.fermentis.tccrecipeservice.model.repository;

import br.com.fermentis.tccrecipeservice.model.entity.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Long> {
    @Query(value = "SELECT * FROM recipe.batches WHERE deleted_at IS NULL AND recipe_id = :recipeId", nativeQuery = true)
    List<Batch> findByRecipe(@Param("recipeId") Long recipeId);
}
