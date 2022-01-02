<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="sushiFusionGsmApp.produit.home.createOrEditLabel" data-cy="ProduitCreateUpdateHeading">Create or edit a Produit</h2>
        <div>
          <div class="form-group" v-if="produit.id">
            <label for="id">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="produit.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="produit-reference">Reference</label>
            <input
              type="text"
              class="form-control"
              name="reference"
              id="produit-reference"
              data-cy="reference"
              :class="{ valid: !$v.produit.reference.$invalid, invalid: $v.produit.reference.$invalid }"
              v-model="$v.produit.reference.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="produit-nom">Nom</label>
            <input
              type="text"
              class="form-control"
              name="nom"
              id="produit-nom"
              data-cy="nom"
              :class="{ valid: !$v.produit.nom.$invalid, invalid: $v.produit.nom.$invalid }"
              v-model="$v.produit.nom.$model"
              required
            />
            <div v-if="$v.produit.nom.$anyDirty && $v.produit.nom.$invalid">
              <small class="form-text text-danger" v-if="!$v.produit.nom.required"> This field is required. </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="produit-prix">Prix</label>
            <input
              type="number"
              class="form-control"
              name="prix"
              id="produit-prix"
              data-cy="prix"
              :class="{ valid: !$v.produit.prix.$invalid, invalid: $v.produit.prix.$invalid }"
              v-model.number="$v.produit.prix.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="produit-ingredient">Ingredient</label>
            <select class="form-control" id="produit-ingredient" data-cy="ingredient" name="ingredient" v-model="produit.ingredient">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="produit.ingredient && elementOption.id === produit.ingredient.id ? produit.ingredient : elementOption"
                v-for="elementOption in elements"
                :key="elementOption.id"
              >
                {{ elementOption.nombre }}
              </option>
            </select>
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" data-cy="entityCreateCancelButton" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span>Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.produit.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./produit-update.component.ts"></script>
