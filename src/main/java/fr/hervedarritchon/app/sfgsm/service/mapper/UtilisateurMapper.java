package fr.hervedarritchon.app.sfgsm.service.mapper;

import fr.hervedarritchon.app.sfgsm.domain.Utilisateur;
import fr.hervedarritchon.app.sfgsm.service.dto.UtilisateurDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Utilisateur} and its DTO {@link UtilisateurDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UtilisateurMapper extends EntityMapper<UtilisateurDTO, Utilisateur> {
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UtilisateurDTO toDtoId(Utilisateur utilisateur);
}
