package fr.hervedarritchon.app.sfgsm.service.impl;

import fr.hervedarritchon.app.sfgsm.domain.Element;
import fr.hervedarritchon.app.sfgsm.repository.ElementRepository;
import fr.hervedarritchon.app.sfgsm.service.ElementService;
import fr.hervedarritchon.app.sfgsm.service.dto.ElementDTO;
import fr.hervedarritchon.app.sfgsm.service.mapper.ElementMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Element}.
 */
@Service
@Transactional
public class ElementServiceImpl implements ElementService {

    private final Logger log = LoggerFactory.getLogger(ElementServiceImpl.class);

    private final ElementRepository elementRepository;

    private final ElementMapper elementMapper;

    public ElementServiceImpl(ElementRepository elementRepository, ElementMapper elementMapper) {
        this.elementRepository = elementRepository;
        this.elementMapper = elementMapper;
    }

    @Override
    public ElementDTO save(ElementDTO elementDTO) {
        log.debug("Request to save Element : {}", elementDTO);
        Element element = elementMapper.toEntity(elementDTO);
        element = elementRepository.save(element);
        return elementMapper.toDto(element);
    }

    @Override
    public Optional<ElementDTO> partialUpdate(ElementDTO elementDTO) {
        log.debug("Request to partially update Element : {}", elementDTO);

        return elementRepository
            .findById(elementDTO.getId())
            .map(existingElement -> {
                elementMapper.partialUpdate(existingElement, elementDTO);

                return existingElement;
            })
            .map(elementRepository::save)
            .map(elementMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ElementDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Elements");
        return elementRepository.findAll(pageable).map(elementMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ElementDTO> findOne(Long id) {
        log.debug("Request to get Element : {}", id);
        return elementRepository.findById(id).map(elementMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Element : {}", id);
        elementRepository.deleteById(id);
    }
}
