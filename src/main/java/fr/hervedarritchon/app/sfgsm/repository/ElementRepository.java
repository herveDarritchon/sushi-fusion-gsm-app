package fr.hervedarritchon.app.sfgsm.repository;

import fr.hervedarritchon.app.sfgsm.domain.Element;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Element entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ElementRepository extends JpaRepository<Element, Long> {}
