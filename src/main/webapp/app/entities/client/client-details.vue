<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="client">
        <h2 class="jh-entity-heading" data-cy="clientDetailsHeading"><span>Client</span> {{ client.id }}</h2>
        <dl class="row jh-entity-details">
          <dt>
            <span>Nom</span>
          </dt>
          <dd>
            <span>{{ client.nom }}</span>
          </dd>
          <dt>
            <span>Adresse</span>
          </dt>
          <dd>
            <span>{{ client.adresse }}</span>
          </dd>
          <dt>
            <span>Type</span>
          </dt>
          <dd>
            <span>{{ client.type }}</span>
          </dd>
          <dt>
            <span>Vendeur</span>
          </dt>
          <dd>
            <div v-if="client.vendeur">
              <router-link :to="{ name: 'UtilisateurView', params: { utilisateurId: client.vendeur.id } }">{{
                client.vendeur.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
        </button>
        <router-link v-if="client.id" :to="{ name: 'ClientEdit', params: { clientId: client.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./client-details.component.ts"></script>
