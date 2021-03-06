package fr.hervedarritchon.app.sfgsm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import fr.hervedarritchon.app.sfgsm.IntegrationTest;
import fr.hervedarritchon.app.sfgsm.domain.Element;
import fr.hervedarritchon.app.sfgsm.repository.ElementRepository;
import fr.hervedarritchon.app.sfgsm.service.ElementService;
import fr.hervedarritchon.app.sfgsm.service.dto.ElementDTO;
import fr.hervedarritchon.app.sfgsm.service.mapper.ElementMapper;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ElementResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class ElementResourceIT {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_END_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/elements";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ElementRepository elementRepository;

    @Mock
    private ElementRepository elementRepositoryMock;

    @Autowired
    private ElementMapper elementMapper;

    @Mock
    private ElementService elementServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restElementMockMvc;

    private Element element;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Element createEntity(EntityManager em) {
        Element element = new Element()
            .nom(DEFAULT_NOM)
            .createdDate(DEFAULT_CREATED_DATE)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE);
        return element;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Element createUpdatedEntity(EntityManager em) {
        Element element = new Element()
            .nom(UPDATED_NOM)
            .createdDate(UPDATED_CREATED_DATE)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE);
        return element;
    }

    @BeforeEach
    public void initTest() {
        element = createEntity(em);
    }

    @Test
    @Transactional
    void createElement() throws Exception {
        int databaseSizeBeforeCreate = elementRepository.findAll().size();
        // Create the Element
        ElementDTO elementDTO = elementMapper.toDto(element);
        restElementMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(elementDTO)))
            .andExpect(status().isCreated());

        // Validate the Element in the database
        List<Element> elementList = elementRepository.findAll();
        assertThat(elementList).hasSize(databaseSizeBeforeCreate + 1);
        Element testElement = elementList.get(elementList.size() - 1);
        assertThat(testElement.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testElement.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testElement.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testElement.getEndDate()).isEqualTo(DEFAULT_END_DATE);
    }

    @Test
    @Transactional
    void createElementWithExistingId() throws Exception {
        // Create the Element with an existing ID
        element.setId(1L);
        ElementDTO elementDTO = elementMapper.toDto(element);

        int databaseSizeBeforeCreate = elementRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restElementMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(elementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Element in the database
        List<Element> elementList = elementRepository.findAll();
        assertThat(elementList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = elementRepository.findAll().size();
        // set the field null
        element.setNom(null);

        // Create the Element, which fails.
        ElementDTO elementDTO = elementMapper.toDto(element);

        restElementMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(elementDTO)))
            .andExpect(status().isBadRequest());

        List<Element> elementList = elementRepository.findAll();
        assertThat(elementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllElements() throws Exception {
        // Initialize the database
        elementRepository.saveAndFlush(element);

        // Get all the elementList
        restElementMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(element.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllElementsWithEagerRelationshipsIsEnabled() throws Exception {
        when(elementServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restElementMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(elementServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllElementsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(elementServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restElementMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(elementServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    void getElement() throws Exception {
        // Initialize the database
        elementRepository.saveAndFlush(element);

        // Get the element
        restElementMockMvc
            .perform(get(ENTITY_API_URL_ID, element.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(element.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingElement() throws Exception {
        // Get the element
        restElementMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewElement() throws Exception {
        // Initialize the database
        elementRepository.saveAndFlush(element);

        int databaseSizeBeforeUpdate = elementRepository.findAll().size();

        // Update the element
        Element updatedElement = elementRepository.findById(element.getId()).get();
        // Disconnect from session so that the updates on updatedElement are not directly saved in db
        em.detach(updatedElement);
        updatedElement.nom(UPDATED_NOM).createdDate(UPDATED_CREATED_DATE).startDate(UPDATED_START_DATE).endDate(UPDATED_END_DATE);
        ElementDTO elementDTO = elementMapper.toDto(updatedElement);

        restElementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, elementDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(elementDTO))
            )
            .andExpect(status().isOk());

        // Validate the Element in the database
        List<Element> elementList = elementRepository.findAll();
        assertThat(elementList).hasSize(databaseSizeBeforeUpdate);
        Element testElement = elementList.get(elementList.size() - 1);
        assertThat(testElement.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testElement.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testElement.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testElement.getEndDate()).isEqualTo(UPDATED_END_DATE);
    }

    @Test
    @Transactional
    void putNonExistingElement() throws Exception {
        int databaseSizeBeforeUpdate = elementRepository.findAll().size();
        element.setId(count.incrementAndGet());

        // Create the Element
        ElementDTO elementDTO = elementMapper.toDto(element);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restElementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, elementDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(elementDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Element in the database
        List<Element> elementList = elementRepository.findAll();
        assertThat(elementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchElement() throws Exception {
        int databaseSizeBeforeUpdate = elementRepository.findAll().size();
        element.setId(count.incrementAndGet());

        // Create the Element
        ElementDTO elementDTO = elementMapper.toDto(element);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restElementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(elementDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Element in the database
        List<Element> elementList = elementRepository.findAll();
        assertThat(elementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamElement() throws Exception {
        int databaseSizeBeforeUpdate = elementRepository.findAll().size();
        element.setId(count.incrementAndGet());

        // Create the Element
        ElementDTO elementDTO = elementMapper.toDto(element);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restElementMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(elementDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Element in the database
        List<Element> elementList = elementRepository.findAll();
        assertThat(elementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateElementWithPatch() throws Exception {
        // Initialize the database
        elementRepository.saveAndFlush(element);

        int databaseSizeBeforeUpdate = elementRepository.findAll().size();

        // Update the element using partial update
        Element partialUpdatedElement = new Element();
        partialUpdatedElement.setId(element.getId());

        partialUpdatedElement.nom(UPDATED_NOM).createdDate(UPDATED_CREATED_DATE).startDate(UPDATED_START_DATE);

        restElementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedElement.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedElement))
            )
            .andExpect(status().isOk());

        // Validate the Element in the database
        List<Element> elementList = elementRepository.findAll();
        assertThat(elementList).hasSize(databaseSizeBeforeUpdate);
        Element testElement = elementList.get(elementList.size() - 1);
        assertThat(testElement.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testElement.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testElement.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testElement.getEndDate()).isEqualTo(DEFAULT_END_DATE);
    }

    @Test
    @Transactional
    void fullUpdateElementWithPatch() throws Exception {
        // Initialize the database
        elementRepository.saveAndFlush(element);

        int databaseSizeBeforeUpdate = elementRepository.findAll().size();

        // Update the element using partial update
        Element partialUpdatedElement = new Element();
        partialUpdatedElement.setId(element.getId());

        partialUpdatedElement.nom(UPDATED_NOM).createdDate(UPDATED_CREATED_DATE).startDate(UPDATED_START_DATE).endDate(UPDATED_END_DATE);

        restElementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedElement.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedElement))
            )
            .andExpect(status().isOk());

        // Validate the Element in the database
        List<Element> elementList = elementRepository.findAll();
        assertThat(elementList).hasSize(databaseSizeBeforeUpdate);
        Element testElement = elementList.get(elementList.size() - 1);
        assertThat(testElement.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testElement.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testElement.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testElement.getEndDate()).isEqualTo(UPDATED_END_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingElement() throws Exception {
        int databaseSizeBeforeUpdate = elementRepository.findAll().size();
        element.setId(count.incrementAndGet());

        // Create the Element
        ElementDTO elementDTO = elementMapper.toDto(element);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restElementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, elementDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(elementDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Element in the database
        List<Element> elementList = elementRepository.findAll();
        assertThat(elementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchElement() throws Exception {
        int databaseSizeBeforeUpdate = elementRepository.findAll().size();
        element.setId(count.incrementAndGet());

        // Create the Element
        ElementDTO elementDTO = elementMapper.toDto(element);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restElementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(elementDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Element in the database
        List<Element> elementList = elementRepository.findAll();
        assertThat(elementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamElement() throws Exception {
        int databaseSizeBeforeUpdate = elementRepository.findAll().size();
        element.setId(count.incrementAndGet());

        // Create the Element
        ElementDTO elementDTO = elementMapper.toDto(element);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restElementMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(elementDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Element in the database
        List<Element> elementList = elementRepository.findAll();
        assertThat(elementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteElement() throws Exception {
        // Initialize the database
        elementRepository.saveAndFlush(element);

        int databaseSizeBeforeDelete = elementRepository.findAll().size();

        // Delete the element
        restElementMockMvc
            .perform(delete(ENTITY_API_URL_ID, element.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Element> elementList = elementRepository.findAll();
        assertThat(elementList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
