package br.com.fermentis.tccrecipeservice.model.repository;

import br.com.fermentis.tccrecipeservice.model.entity.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    @Query(value="SELECT * FROM recipe.recipes WHERE deleted_at IS NULL ORDER BY recipe_id DESC",
            countQuery="SELECT COUNT(1) FROM recipe.recipes WHERE deleted_at IS NULL",
            nativeQuery = true
    )
    Page<Recipe> findRecipes(Pageable pageable);
}
