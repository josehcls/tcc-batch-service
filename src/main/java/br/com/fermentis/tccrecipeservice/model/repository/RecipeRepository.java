package br.com.fermentis.tccrecipeservice.model.repository;

import br.com.fermentis.tccrecipeservice.model.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
