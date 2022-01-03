package fr.hervedarritchon.app.sfgsm.service.mapper;

import fr.hervedarritchon.app.sfgsm.domain.Element;
import fr.hervedarritchon.app.sfgsm.service.dto.ElementDTO;
import java.util.Set;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Element} and its DTO {@link ElementDTO}.
 */
@Mapper(componentModel = "spring", uses = { ProduitMapper.class })
public interface ElementMapper extends EntityMapper<ElementDTO, Element> {
    @Mapping(target = "produits", source = "produits", qualifiedByName = "idSet")
    ElementDTO toDto(Element s);

    @Mapping(target = "removeProduit", ignore = true)
    Element toEntity(ElementDTO elementDTO);
}
