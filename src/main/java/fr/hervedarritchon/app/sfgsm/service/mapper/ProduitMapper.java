package fr.hervedarritchon.app.sfgsm.service.mapper;

import fr.hervedarritchon.app.sfgsm.domain.Produit;
import fr.hervedarritchon.app.sfgsm.service.dto.ProduitDTO;
import java.util.Set;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Produit} and its DTO {@link ProduitDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProduitMapper extends EntityMapper<ProduitDTO, Produit> {
    @Named("idSet")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    Set<ProduitDTO> toDtoIdSet(Set<Produit> produit);
}
