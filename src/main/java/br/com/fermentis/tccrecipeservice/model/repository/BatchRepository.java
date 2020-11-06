package br.com.fermentis.tccrecipeservice.model.repository;

import br.com.fermentis.tccrecipeservice.model.entity.Batch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Long> {
    @Query(value="SELECT * FROM recipe.batches WHERE recipe_id = :recipeId AND name LIKE :query AND deleted_at IS NULL ORDER BY batch_id DESC",
            countQuery="SELECT COUNT(1) FROM recipe.batches WHERE recipe_id = :recipeId AND name LIKE :query deleted_at IS NULL",
            nativeQuery = true
    )
    Page<Batch> getByRecipe(@Param("recipeId") Long recipeId, @Param("query") String query, Pageable pageable);
}
