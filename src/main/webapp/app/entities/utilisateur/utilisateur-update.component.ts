import { Component, Vue, Inject } from 'vue-property-decorator';

import { required } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';

import ClientService from '@/entities/client/client.service';
import { IClient } from '@/shared/model/client.model';

import { IUtilisateur, Utilisateur } from '@/shared/model/utilisateur.model';
import UtilisateurService from './utilisateur.service';
import { RoleEnum } from '@/shared/model/enumerations/role-enum.model';

const validations: any = {
  utilisateur: {
    nom: {
      required,
    },
    role: {
      required,
    },
    createdDate: {},
    startDate: {},
    endDate: {},
  },
};

@Component({
  validations,
})
export default class UtilisateurUpdate extends Vue {
  @Inject('utilisateurService') private utilisateurService: () => UtilisateurService;
  @Inject('alertService') private alertService: () => AlertService;

  public utilisateur: IUtilisateur = new Utilisateur();

  @Inject('clientService') private clientService: () => ClientService;

  public clients: IClient[] = [];
  public roleEnumValues: string[] = Object.keys(RoleEnum);
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.utilisateurId) {
        vm.retrieveUtilisateur(to.params.utilisateurId);
      }
      vm.initRelationships();
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;
    if (this.utilisateur.id) {
      this.utilisateurService()
        .update(this.utilisateur)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A Utilisateur is updated with identifier ' + param.id;
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        })
        .catch(error => {
          this.isSaving = false;
          this.alertService().showHttpError(this, error.response);
        });
    } else {
      this.utilisateurService()
        .create(this.utilisateur)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A Utilisateur is created with identifier ' + param.id;
          this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Success',
            variant: 'success',
            solid: true,
            autoHideDelay: 5000,
          });
        })
        .catch(error => {
          this.isSaving = false;
          this.alertService().showHttpError(this, error.response);
        });
    }
  }

  public retrieveUtilisateur(utilisateurId): void {
    this.utilisateurService()
      .find(utilisateurId)
      .then(res => {
        this.utilisateur = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.clientService()
      .retrieve()
      .then(res => {
        this.clients = res.data;
      });
  }
}
