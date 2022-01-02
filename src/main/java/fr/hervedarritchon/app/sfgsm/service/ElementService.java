package fr.hervedarritchon.app.sfgsm.service;

import fr.hervedarritchon.app.sfgsm.service.dto.ElementDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link fr.hervedarritchon.app.sfgsm.domain.Element}.
 */
public interface ElementService {
    /**
     * Save a element.
     *
     * @param elementDTO the entity to save.
     * @return the persisted entity.
     */
    ElementDTO save(ElementDTO elementDTO);

    /**
     * Partially updates a element.
     *
     * @param elementDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ElementDTO> partialUpdate(ElementDTO elementDTO);

    /**
     * Get all the elements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ElementDTO> findAll(Pageable pageable);

    /**
     * Get the "id" element.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ElementDTO> findOne(Long id);

    /**
     * Delete the "id" element.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
