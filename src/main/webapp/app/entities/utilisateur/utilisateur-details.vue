<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="utilisateur">
        <h2 class="jh-entity-heading" data-cy="utilisateurDetailsHeading"><span>Utilisateur</span> {{ utilisateur.id }}</h2>
        <dl class="row jh-entity-details">
          <dt>
            <span>Nom</span>
          </dt>
          <dd>
            <span>{{ utilisateur.nom }}</span>
          </dd>
          <dt>
            <span>Role</span>
          </dt>
          <dd>
            <span>{{ utilisateur.role }}</span>
          </dd>
          <dt>
            <span>Created Date</span>
          </dt>
          <dd>
            <span>{{ utilisateur.createdDate }}</span>
          </dd>
          <dt>
            <span>Start Date</span>
          </dt>
          <dd>
            <span>{{ utilisateur.startDate }}</span>
          </dd>
          <dt>
            <span>End Date</span>
          </dt>
          <dd>
            <span>{{ utilisateur.endDate }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
        </button>
        <router-link
          v-if="utilisateur.id"
          :to="{ name: 'UtilisateurEdit', params: { utilisateurId: utilisateur.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./utilisateur-details.component.ts"></script>
