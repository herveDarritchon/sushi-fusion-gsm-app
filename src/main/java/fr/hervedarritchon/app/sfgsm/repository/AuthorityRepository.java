package fr.hervedarritchon.app.sfgsm.repository;

import fr.hervedarritchon.app.sfgsm.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {}
