package fr.hervedarritchon.app.sfgsm.repository;

import fr.hervedarritchon.app.sfgsm.domain.Element;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Element entity.
 */
@Repository
public interface ElementRepository extends JpaRepository<Element, Long> {
    @Query(
        value = "select distinct element from Element element left join fetch element.produits",
        countQuery = "select count(distinct element) from Element element"
    )
    Page<Element> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct element from Element element left join fetch element.produits")
    List<Element> findAllWithEagerRelationships();

    @Query("select element from Element element left join fetch element.produits where element.id =:id")
    Optional<Element> findOneWithEagerRelationships(@Param("id") Long id);
}
