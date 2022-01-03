<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="element">
        <h2 class="jh-entity-heading" data-cy="elementDetailsHeading"><span>Element</span> {{ element.id }}</h2>
        <dl class="row jh-entity-details">
          <dt>
            <span>Nom</span>
          </dt>
          <dd>
            <span>{{ element.nom }}</span>
          </dd>
          <dt>
            <span>Created Date</span>
          </dt>
          <dd>
            <span>{{ element.createdDate }}</span>
          </dd>
          <dt>
            <span>Start Date</span>
          </dt>
          <dd>
            <span>{{ element.startDate }}</span>
          </dd>
          <dt>
            <span>End Date</span>
          </dt>
          <dd>
            <span>{{ element.endDate }}</span>
          </dd>
          <dt>
            <span>Produit</span>
          </dt>
          <dd>
            <span v-for="(produit, i) in element.produits" :key="produit.id"
              >{{ i > 0 ? ', ' : '' }}
              <router-link :to="{ name: 'ProduitView', params: { produitId: produit.id } }">{{ produit.id }}</router-link>
            </span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
        </button>
        <router-link v-if="element.id" :to="{ name: 'ElementEdit', params: { elementId: element.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./element-details.component.ts"></script>
