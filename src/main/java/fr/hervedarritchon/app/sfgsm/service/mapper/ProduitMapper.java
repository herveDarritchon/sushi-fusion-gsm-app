package fr.hervedarritchon.app.sfgsm.service.mapper;

import fr.hervedarritchon.app.sfgsm.domain.Produit;
import fr.hervedarritchon.app.sfgsm.service.dto.ProduitDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Produit} and its DTO {@link ProduitDTO}.
 */
@Mapper(componentModel = "spring", uses = { ElementMapper.class })
public interface ProduitMapper extends EntityMapper<ProduitDTO, Produit> {
    @Mapping(target = "ingredient", source = "ingredient", qualifiedByName = "nombre")
    ProduitDTO toDto(Produit s);
}
