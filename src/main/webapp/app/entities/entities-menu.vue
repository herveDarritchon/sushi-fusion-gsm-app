<template>
  <div>
    <b-dropdown-item to="/client">
      <font-awesome-icon icon="asterisk" />
      <span>Client</span>
    </b-dropdown-item>
    <b-dropdown-item to="/produit">
      <font-awesome-icon icon="asterisk" />
      <span>Produit</span>
    </b-dropdown-item>
    <b-dropdown-item to="/element">
      <font-awesome-icon icon="asterisk" />
      <span>Element</span>
    </b-dropdown-item>
    <b-dropdown-item to="/utilisateur">
      <font-awesome-icon icon="asterisk" />
      <span>Utilisateur</span>
    </b-dropdown-item>
    <!-- jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here -->
  </div>
</template>

<script lang="ts" src="./entities-menu.component.ts"></script>
