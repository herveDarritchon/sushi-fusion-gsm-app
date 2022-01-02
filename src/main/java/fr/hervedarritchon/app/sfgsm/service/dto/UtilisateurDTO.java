package fr.hervedarritchon.app.sfgsm.service.dto;

import fr.hervedarritchon.app.sfgsm.domain.enumeration.RoleEnum;
import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link fr.hervedarritchon.app.sfgsm.domain.Utilisateur} entity.
 */
public class UtilisateurDTO implements Serializable {

    private Long id;

    @NotNull
    private String nom;

    @NotNull
    private RoleEnum role;

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

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UtilisateurDTO)) {
            return false;
        }

        UtilisateurDTO utilisateurDTO = (UtilisateurDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, utilisateurDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UtilisateurDTO{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", role='" + getRole() + "'" +
            "}";
    }
}
