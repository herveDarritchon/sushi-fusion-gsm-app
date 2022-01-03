<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="sushiFusionGsmApp.element.home.createOrEditLabel" data-cy="ElementCreateUpdateHeading">Create or edit a Element</h2>
        <div>
          <div class="form-group" v-if="element.id">
            <label for="id">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="element.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="element-nom">Nom</label>
            <input
              type="text"
              class="form-control"
              name="nom"
              id="element-nom"
              data-cy="nom"
              :class="{ valid: !$v.element.nom.$invalid, invalid: $v.element.nom.$invalid }"
              v-model="$v.element.nom.$model"
              required
            />
            <div v-if="$v.element.nom.$anyDirty && $v.element.nom.$invalid">
              <small class="form-text text-danger" v-if="!$v.element.nom.required"> This field is required. </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="element-createdDate">Created Date</label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="element-createdDate"
                  v-model="$v.element.createdDate.$model"
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
                id="element-createdDate"
                data-cy="createdDate"
                type="text"
                class="form-control"
                name="createdDate"
                :class="{ valid: !$v.element.createdDate.$invalid, invalid: $v.element.createdDate.$invalid }"
                v-model="$v.element.createdDate.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="element-startDate">Start Date</label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="element-startDate"
                  v-model="$v.element.startDate.$model"
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
                id="element-startDate"
                data-cy="startDate"
                type="text"
                class="form-control"
                name="startDate"
                :class="{ valid: !$v.element.startDate.$invalid, invalid: $v.element.startDate.$invalid }"
                v-model="$v.element.startDate.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="element-endDate">End Date</label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="element-endDate"
                  v-model="$v.element.endDate.$model"
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
                id="element-endDate"
                data-cy="endDate"
                type="text"
                class="form-control"
                name="endDate"
                :class="{ valid: !$v.element.endDate.$invalid, invalid: $v.element.endDate.$invalid }"
                v-model="$v.element.endDate.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label for="element-produit">Produit</label>
            <select
              class="form-control"
              id="element-produits"
              data-cy="produit"
              multiple
              name="produit"
              v-if="element.produits !== undefined"
              v-model="element.produits"
            >
              <option v-bind:value="getSelected(element.produits, produitOption)" v-for="produitOption in produits" :key="produitOption.id">
                {{ produitOption.id }}
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
            :disabled="$v.element.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./element-update.component.ts"></script>
