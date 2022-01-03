<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="produit">
        <h2 class="jh-entity-heading" data-cy="produitDetailsHeading"><span>Produit</span> {{ produit.id }}</h2>
        <dl class="row jh-entity-details">
          <dt>
            <span>Reference</span>
          </dt>
          <dd>
            <span>{{ produit.reference }}</span>
          </dd>
          <dt>
            <span>Nom</span>
          </dt>
          <dd>
            <span>{{ produit.nom }}</span>
          </dd>
          <dt>
            <span>Prix</span>
          </dt>
          <dd>
            <span>{{ produit.prix }}</span>
          </dd>
          <dt>
            <span>Created Date</span>
          </dt>
          <dd>
            <span>{{ produit.createdDate }}</span>
          </dd>
          <dt>
            <span>Start Date</span>
          </dt>
          <dd>
            <span>{{ produit.startDate }}</span>
          </dd>
          <dt>
            <span>End Date</span>
          </dt>
          <dd>
            <span>{{ produit.endDate }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
        </button>
        <router-link v-if="produit.id" :to="{ name: 'ProduitEdit', params: { produitId: produit.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./produit-details.component.ts"></script>
