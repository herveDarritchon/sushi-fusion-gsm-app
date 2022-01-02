package fr.hervedarritchon.app.sfgsm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Produit.
 */
@Entity
@Table(name = "produit")
public class Produit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "reference")
    private String reference;

    @NotNull
    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "prix")
    private Integer prix;

    @ManyToOne
    @JsonIgnoreProperties(value = { "produits" }, allowSetters = true)
    private Element ingredient;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Produit id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return this.reference;
    }

    public Produit reference(String reference) {
        this.setReference(reference);
        return this;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getNom() {
        return this.nom;
    }

    public Produit nom(String nom) {
        this.setNom(nom);
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getPrix() {
        return this.prix;
    }

    public Produit prix(Integer prix) {
        this.setPrix(prix);
        return this;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }

    public Element getIngredient() {
        return this.ingredient;
    }

    public void setIngredient(Element element) {
        this.ingredient = element;
    }

    public Produit ingredient(Element element) {
        this.setIngredient(element);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Produit)) {
            return false;
        }
        return id != null && id.equals(((Produit) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Produit{" +
            "id=" + getId() +
            ", reference='" + getReference() + "'" +
            ", nom='" + getNom() + "'" +
            ", prix=" + getPrix() +
            "}";
    }
}
