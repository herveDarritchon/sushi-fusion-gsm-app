import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore
const Entities = () => import('@/entities/entities.vue');

// prettier-ignore
const Client = () => import('@/entities/client/client.vue');
// prettier-ignore
const ClientUpdate = () => import('@/entities/client/client-update.vue');
// prettier-ignore
const ClientDetails = () => import('@/entities/client/client-details.vue');
// prettier-ignore
const Produit = () => import('@/entities/produit/produit.vue');
// prettier-ignore
const ProduitUpdate = () => import('@/entities/produit/produit-update.vue');
// prettier-ignore
const ProduitDetails = () => import('@/entities/produit/produit-details.vue');
// prettier-ignore
const Element = () => import('@/entities/element/element.vue');
// prettier-ignore
const ElementUpdate = () => import('@/entities/element/element-update.vue');
// prettier-ignore
const ElementDetails = () => import('@/entities/element/element-details.vue');
// prettier-ignore
const Utilisateur = () => import('@/entities/utilisateur/utilisateur.vue');
// prettier-ignore
const UtilisateurUpdate = () => import('@/entities/utilisateur/utilisateur-update.vue');
// prettier-ignore
const UtilisateurDetails = () => import('@/entities/utilisateur/utilisateur-details.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default {
  path: '/',
  component: Entities,
  children: [
    {
      path: 'client',
      name: 'Client',
      component: Client,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'client/new',
      name: 'ClientCreate',
      component: ClientUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'client/:clientId/edit',
      name: 'ClientEdit',
      component: ClientUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'client/:clientId/view',
      name: 'ClientView',
      component: ClientDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'produit',
      name: 'Produit',
      component: Produit,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'produit/new',
      name: 'ProduitCreate',
      component: ProduitUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'produit/:produitId/edit',
      name: 'ProduitEdit',
      component: ProduitUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'produit/:produitId/view',
      name: 'ProduitView',
      component: ProduitDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'element',
      name: 'Element',
      component: Element,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'element/new',
      name: 'ElementCreate',
      component: ElementUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'element/:elementId/edit',
      name: 'ElementEdit',
      component: ElementUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'element/:elementId/view',
      name: 'ElementView',
      component: ElementDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'utilisateur',
      name: 'Utilisateur',
      component: Utilisateur,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'utilisateur/new',
      name: 'UtilisateurCreate',
      component: UtilisateurUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'utilisateur/:utilisateurId/edit',
      name: 'UtilisateurEdit',
      component: UtilisateurUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'utilisateur/:utilisateurId/view',
      name: 'UtilisateurView',
      component: UtilisateurDetails,
      meta: { authorities: [Authority.USER] },
    },
    // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
  ],
};
