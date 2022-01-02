<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="sushiFusionGsmApp.client.home.createOrEditLabel" data-cy="ClientCreateUpdateHeading">Create or edit a Client</h2>
        <div>
          <div class="form-group" v-if="client.id">
            <label for="id">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="client.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="client-nom">Nom</label>
            <input
              type="text"
              class="form-control"
              name="nom"
              id="client-nom"
              data-cy="nom"
              :class="{ valid: !$v.client.nom.$invalid, invalid: $v.client.nom.$invalid }"
              v-model="$v.client.nom.$model"
              required
            />
            <div v-if="$v.client.nom.$anyDirty && $v.client.nom.$invalid">
              <small class="form-text text-danger" v-if="!$v.client.nom.required"> This field is required. </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="client-adresse">Adresse</label>
            <input
              type="text"
              class="form-control"
              name="adresse"
              id="client-adresse"
              data-cy="adresse"
              :class="{ valid: !$v.client.adresse.$invalid, invalid: $v.client.adresse.$invalid }"
              v-model="$v.client.adresse.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="client-type">Type</label>
            <select
              class="form-control"
              name="type"
              :class="{ valid: !$v.client.type.$invalid, invalid: $v.client.type.$invalid }"
              v-model="$v.client.type.$model"
              id="client-type"
              data-cy="type"
            >
              <option v-for="clientEnum in clientEnumValues" :key="clientEnum" v-bind:value="clientEnum">{{ clientEnum }}</option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="client-vendeur">Vendeur</label>
            <select class="form-control" id="client-vendeur" data-cy="vendeur" name="vendeur" v-model="client.vendeur">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="client.vendeur && utilisateurOption.id === client.vendeur.id ? client.vendeur : utilisateurOption"
                v-for="utilisateurOption in utilisateurs"
                :key="utilisateurOption.id"
              >
                {{ utilisateurOption.id }}
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
            :disabled="$v.client.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./client-update.component.ts"></script>
