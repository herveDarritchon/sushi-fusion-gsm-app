package fr.hervedarritchon.app.sfgsm.service;

import fr.hervedarritchon.app.sfgsm.service.dto.ProduitDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link fr.hervedarritchon.app.sfgsm.domain.Produit}.
 */
public interface ProduitService {
    /**
     * Save a produit.
     *
     * @param produitDTO the entity to save.
     * @return the persisted entity.
     */
    ProduitDTO save(ProduitDTO produitDTO);

    /**
     * Partially updates a produit.
     *
     * @param produitDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ProduitDTO> partialUpdate(ProduitDTO produitDTO);

    /**
     * Get all the produits.
     *
     * @return the list of entities.
     */
    List<ProduitDTO> findAll();

    /**
     * Get the "id" produit.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProduitDTO> findOne(Long id);

    /**
     * Delete the "id" produit.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
