package fr.hervedarritchon.app.sfgsm.service.mapper;

import fr.hervedarritchon.app.sfgsm.domain.Element;
import fr.hervedarritchon.app.sfgsm.service.dto.ElementDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Element} and its DTO {@link ElementDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ElementMapper extends EntityMapper<ElementDTO, Element> {
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ElementDTO toDtoId(Element element);
}
