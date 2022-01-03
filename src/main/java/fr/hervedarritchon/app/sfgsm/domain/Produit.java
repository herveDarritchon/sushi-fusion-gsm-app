package fr.hervedarritchon.app.sfgsm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
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

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToMany(mappedBy = "produits")
    @JsonIgnoreProperties(value = { "produits" }, allowSetters = true)
    private Set<Element> elements = new HashSet<>();

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

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public Produit createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public Produit startDate(LocalDate startDate) {
        this.setStartDate(startDate);
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public Produit endDate(LocalDate endDate) {
        this.setEndDate(endDate);
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Set<Element> getElements() {
        return this.elements;
    }

    public void setElements(Set<Element> elements) {
        if (this.elements != null) {
            this.elements.forEach(i -> i.removeProduit(this));
        }
        if (elements != null) {
            elements.forEach(i -> i.addProduit(this));
        }
        this.elements = elements;
    }

    public Produit elements(Set<Element> elements) {
        this.setElements(elements);
        return this;
    }

    public Produit addElement(Element element) {
        this.elements.add(element);
        element.getProduits().add(this);
        return this;
    }

    public Produit removeElement(Element element) {
        this.elements.remove(element);
        element.getProduits().remove(this);
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
            ", createdDate='" + getCreatedDate() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            "}";
    }
}
