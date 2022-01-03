package fr.hervedarritchon.app.sfgsm.web.rest;

import fr.hervedarritchon.app.sfgsm.repository.ElementRepository;
import fr.hervedarritchon.app.sfgsm.service.ElementService;
import fr.hervedarritchon.app.sfgsm.service.dto.ElementDTO;
import fr.hervedarritchon.app.sfgsm.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link fr.hervedarritchon.app.sfgsm.domain.Element}.
 */
@RestController
@RequestMapping("/api")
public class ElementResource {

    private final Logger log = LoggerFactory.getLogger(ElementResource.class);

    private static final String ENTITY_NAME = "element";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ElementService elementService;

    private final ElementRepository elementRepository;

    public ElementResource(ElementService elementService, ElementRepository elementRepository) {
        this.elementService = elementService;
        this.elementRepository = elementRepository;
    }

    /**
     * {@code POST  /elements} : Create a new element.
     *
     * @param elementDTO the elementDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new elementDTO, or with status {@code 400 (Bad Request)} if the element has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/elements")
    public ResponseEntity<ElementDTO> createElement(@Valid @RequestBody ElementDTO elementDTO) throws URISyntaxException {
        log.debug("REST request to save Element : {}", elementDTO);
        if (elementDTO.getId() != null) {
            throw new BadRequestAlertException("A new element cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ElementDTO result = elementService.save(elementDTO);
        return ResponseEntity
            .created(new URI("/api/elements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /elements/:id} : Updates an existing element.
     *
     * @param id the id of the elementDTO to save.
     * @param elementDTO the elementDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated elementDTO,
     * or with status {@code 400 (Bad Request)} if the elementDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the elementDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/elements/{id}")
    public ResponseEntity<ElementDTO> updateElement(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ElementDTO elementDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Element : {}, {}", id, elementDTO);
        if (elementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, elementDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!elementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ElementDTO result = elementService.save(elementDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, elementDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /elements/:id} : Partial updates given fields of an existing element, field will ignore if it is null
     *
     * @param id the id of the elementDTO to save.
     * @param elementDTO the elementDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated elementDTO,
     * or with status {@code 400 (Bad Request)} if the elementDTO is not valid,
     * or with status {@code 404 (Not Found)} if the elementDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the elementDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/elements/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ElementDTO> partialUpdateElement(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ElementDTO elementDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Element partially : {}, {}", id, elementDTO);
        if (elementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, elementDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!elementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ElementDTO> result = elementService.partialUpdate(elementDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, elementDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /elements} : get all the elements.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of elements in body.
     */
    @GetMapping("/elements")
    public ResponseEntity<List<ElementDTO>> getAllElements(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        @RequestParam(required = false, defaultValue = "false") boolean eagerload
    ) {
        log.debug("REST request to get a page of Elements");
        Page<ElementDTO> page;
        if (eagerload) {
            page = elementService.findAllWithEagerRelationships(pageable);
        } else {
            page = elementService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /elements/:id} : get the "id" element.
     *
     * @param id the id of the elementDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the elementDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/elements/{id}")
    public ResponseEntity<ElementDTO> getElement(@PathVariable Long id) {
        log.debug("REST request to get Element : {}", id);
        Optional<ElementDTO> elementDTO = elementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(elementDTO);
    }

    /**
     * {@code DELETE  /elements/:id} : delete the "id" element.
     *
     * @param id the id of the elementDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/elements/{id}")
    public ResponseEntity<Void> deleteElement(@PathVariable Long id) {
        log.debug("REST request to delete Element : {}", id);
        elementService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
