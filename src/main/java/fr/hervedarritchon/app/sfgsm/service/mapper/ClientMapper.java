package fr.hervedarritchon.app.sfgsm.service.mapper;

import fr.hervedarritchon.app.sfgsm.domain.Client;
import fr.hervedarritchon.app.sfgsm.service.dto.ClientDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Client} and its DTO {@link ClientDTO}.
 */
@Mapper(componentModel = "spring", uses = { UtilisateurMapper.class })
public interface ClientMapper extends EntityMapper<ClientDTO, Client> {
    @Mapping(target = "vendeur", source = "vendeur", qualifiedByName = "id")
    ClientDTO toDto(Client s);
}
