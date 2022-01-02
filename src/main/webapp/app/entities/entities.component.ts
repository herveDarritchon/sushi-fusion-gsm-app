import { Component, Provide, Vue } from 'vue-property-decorator';

import UserService from '@/entities/user/user.service';
import ClientService from './client/client.service';
import ProduitService from './produit/produit.service';
import ElementService from './element/element.service';
import UtilisateurService from './utilisateur/utilisateur.service';
// jhipster-needle-add-entity-service-to-entities-component-import - JHipster will import entities services here

@Component
export default class Entities extends Vue {
  @Provide('userService') private userService = () => new UserService();
  @Provide('clientService') private clientService = () => new ClientService();
  @Provide('produitService') private produitService = () => new ProduitService();
  @Provide('elementService') private elementService = () => new ElementService();
  @Provide('utilisateurService') private utilisateurService = () => new UtilisateurService();
  // jhipster-needle-add-entity-service-to-entities-component - JHipster will import entities services here
}
