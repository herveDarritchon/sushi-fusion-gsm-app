package fr.hervedarritchon.app.sfgsm.service.impl;

import fr.hervedarritchon.app.sfgsm.domain.Utilisateur;
import fr.hervedarritchon.app.sfgsm.repository.UtilisateurRepository;
import fr.hervedarritchon.app.sfgsm.service.UtilisateurService;
import fr.hervedarritchon.app.sfgsm.service.dto.UtilisateurDTO;
import fr.hervedarritchon.app.sfgsm.service.mapper.UtilisateurMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Utilisateur}.
 */
@Service
@Transactional
public class UtilisateurServiceImpl implements UtilisateurService {

    private final Logger log = LoggerFactory.getLogger(UtilisateurServiceImpl.class);

    private final UtilisateurRepository utilisateurRepository;

    private final UtilisateurMapper utilisateurMapper;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository, UtilisateurMapper utilisateurMapper) {
        this.utilisateurRepository = utilisateurRepository;
        this.utilisateurMapper = utilisateurMapper;
    }

    @Override
    public UtilisateurDTO save(UtilisateurDTO utilisateurDTO) {
        log.debug("Request to save Utilisateur : {}", utilisateurDTO);
        Utilisateur utilisateur = utilisateurMapper.toEntity(utilisateurDTO);
        utilisateur = utilisateurRepository.save(utilisateur);
        return utilisateurMapper.toDto(utilisateur);
    }

    @Override
    public Optional<UtilisateurDTO> partialUpdate(UtilisateurDTO utilisateurDTO) {
        log.debug("Request to partially update Utilisateur : {}", utilisateurDTO);

        return utilisateurRepository
            .findById(utilisateurDTO.getId())
            .map(existingUtilisateur -> {
                utilisateurMapper.partialUpdate(existingUtilisateur, utilisateurDTO);

                return existingUtilisateur;
            })
            .map(utilisateurRepository::save)
            .map(utilisateurMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UtilisateurDTO> findAll() {
        log.debug("Request to get all Utilisateurs");
        return utilisateurRepository.findAll().stream().map(utilisateurMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get all the utilisateurs where Client is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<UtilisateurDTO> findAllWhereClientIsNull() {
        log.debug("Request to get all utilisateurs where Client is null");
        return StreamSupport
            .stream(utilisateurRepository.findAll().spliterator(), false)
            .filter(utilisateur -> utilisateur.getClient() == null)
            .map(utilisateurMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UtilisateurDTO> findOne(Long id) {
        log.debug("Request to get Utilisateur : {}", id);
        return utilisateurRepository.findById(id).map(utilisateurMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Utilisateur : {}", id);
        utilisateurRepository.deleteById(id);
    }
}
