package br.com.fermentis.tccrecipeservice.model.repository;

import br.com.fermentis.tccrecipeservice.model.entity.ControlProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ControlProfileRepository extends JpaRepository<ControlProfile, Long> {

    @Query(value="SELECT * FROM control.control_profiles WHERE name LIKE :query AND deleted_at IS NULL ORDER BY control_profile_id DESC",
            countQuery="SELECT COUNT(1) FROM control.control_profiles WHERE name LIKE :query AND deleted_at IS NULL",
            nativeQuery = true
    )
    Page<ControlProfile> getControlProfiles(@Param("query") String query, Pageable pageable);
}
