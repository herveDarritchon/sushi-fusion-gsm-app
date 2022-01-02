package fr.hervedarritchon.app.sfgsm.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link fr.hervedarritchon.app.sfgsm.domain.Element} entity.
 */
public class ElementDTO implements Serializable {

    private Long id;

    @NotNull
    private String nom;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ElementDTO)) {
            return false;
        }

        ElementDTO elementDTO = (ElementDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, elementDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ElementDTO{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            "}";
    }
}
