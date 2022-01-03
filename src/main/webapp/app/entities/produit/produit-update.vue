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
            <label class="form-control-label" for="produit-createdDate">Created Date</label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="produit-createdDate"
                  v-model="$v.produit.createdDate.$model"
                  name="createdDate"
                  class="form-control"
                  :locale="currentLanguage"
                  button-only
                  today-button
                  reset-button
                  close-button
                >
                </b-form-datepicker>
              </b-input-group-prepend>
              <b-form-input
                id="produit-createdDate"
                data-cy="createdDate"
                type="text"
                class="form-control"
                name="createdDate"
                :class="{ valid: !$v.produit.createdDate.$invalid, invalid: $v.produit.createdDate.$invalid }"
                v-model="$v.produit.createdDate.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="produit-startDate">Start Date</label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="produit-startDate"
                  v-model="$v.produit.startDate.$model"
                  name="startDate"
                  class="form-control"
                  :locale="currentLanguage"
                  button-only
                  today-button
                  reset-button
                  close-button
                >
                </b-form-datepicker>
              </b-input-group-prepend>
              <b-form-input
                id="produit-startDate"
                data-cy="startDate"
                type="text"
                class="form-control"
                name="startDate"
                :class="{ valid: !$v.produit.startDate.$invalid, invalid: $v.produit.startDate.$invalid }"
                v-model="$v.produit.startDate.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="produit-endDate">End Date</label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="produit-endDate"
                  v-model="$v.produit.endDate.$model"
                  name="endDate"
                  class="form-control"
                  :locale="currentLanguage"
                  button-only
                  today-button
                  reset-button
                  close-button
                >
                </b-form-datepicker>
              </b-input-group-prepend>
              <b-form-input
                id="produit-endDate"
                data-cy="endDate"
                type="text"
                class="form-control"
                name="endDate"
                :class="{ valid: !$v.produit.endDate.$invalid, invalid: $v.produit.endDate.$invalid }"
                v-model="$v.produit.endDate.$model"
              />
            </b-input-group>
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
