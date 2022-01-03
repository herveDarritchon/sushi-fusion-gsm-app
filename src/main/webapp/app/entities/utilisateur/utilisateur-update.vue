<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="sushiFusionGsmApp.utilisateur.home.createOrEditLabel" data-cy="UtilisateurCreateUpdateHeading">
          Create or edit a Utilisateur
        </h2>
        <div>
          <div class="form-group" v-if="utilisateur.id">
            <label for="id">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="utilisateur.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="utilisateur-nom">Nom</label>
            <input
              type="text"
              class="form-control"
              name="nom"
              id="utilisateur-nom"
              data-cy="nom"
              :class="{ valid: !$v.utilisateur.nom.$invalid, invalid: $v.utilisateur.nom.$invalid }"
              v-model="$v.utilisateur.nom.$model"
              required
            />
            <div v-if="$v.utilisateur.nom.$anyDirty && $v.utilisateur.nom.$invalid">
              <small class="form-text text-danger" v-if="!$v.utilisateur.nom.required"> This field is required. </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="utilisateur-role">Role</label>
            <select
              class="form-control"
              name="role"
              :class="{ valid: !$v.utilisateur.role.$invalid, invalid: $v.utilisateur.role.$invalid }"
              v-model="$v.utilisateur.role.$model"
              id="utilisateur-role"
              data-cy="role"
              required
            >
              <option v-for="roleEnum in roleEnumValues" :key="roleEnum" v-bind:value="roleEnum">{{ roleEnum }}</option>
            </select>
            <div v-if="$v.utilisateur.role.$anyDirty && $v.utilisateur.role.$invalid">
              <small class="form-text text-danger" v-if="!$v.utilisateur.role.required"> This field is required. </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="utilisateur-createdDate">Created Date</label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="utilisateur-createdDate"
                  v-model="$v.utilisateur.createdDate.$model"
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
                id="utilisateur-createdDate"
                data-cy="createdDate"
                type="text"
                class="form-control"
                name="createdDate"
                :class="{ valid: !$v.utilisateur.createdDate.$invalid, invalid: $v.utilisateur.createdDate.$invalid }"
                v-model="$v.utilisateur.createdDate.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="utilisateur-startDate">Start Date</label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="utilisateur-startDate"
                  v-model="$v.utilisateur.startDate.$model"
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
                id="utilisateur-startDate"
                data-cy="startDate"
                type="text"
                class="form-control"
                name="startDate"
                :class="{ valid: !$v.utilisateur.startDate.$invalid, invalid: $v.utilisateur.startDate.$invalid }"
                v-model="$v.utilisateur.startDate.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="utilisateur-endDate">End Date</label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="utilisateur-endDate"
                  v-model="$v.utilisateur.endDate.$model"
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
                id="utilisateur-endDate"
                data-cy="endDate"
                type="text"
                class="form-control"
                name="endDate"
                :class="{ valid: !$v.utilisateur.endDate.$invalid, invalid: $v.utilisateur.endDate.$invalid }"
                v-model="$v.utilisateur.endDate.$model"
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
            :disabled="$v.utilisateur.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./utilisateur-update.component.ts"></script>
