package fr.hervedarritchon.app.sfgsm.service.dto;

import fr.hervedarritchon.app.sfgsm.domain.enumeration.ClientEnum;
import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link fr.hervedarritchon.app.sfgsm.domain.Client} entity.
 */
public class ClientDTO implements Serializable {

    private Long id;

    @NotNull
    private String nom;

    private String adresse;

    private ClientEnum type;

    private UtilisateurDTO vendeur;

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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public ClientEnum getType() {
        return type;
    }

    public void setType(ClientEnum type) {
        this.type = type;
    }

    public UtilisateurDTO getVendeur() {
        return vendeur;
    }

    public void setVendeur(UtilisateurDTO vendeur) {
        this.vendeur = vendeur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClientDTO)) {
            return false;
        }

        ClientDTO clientDTO = (ClientDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, clientDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClientDTO{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", type='" + getType() + "'" +
            ", vendeur=" + getVendeur() +
            "}";
    }
}
