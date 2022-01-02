import { Component, Vue, Inject } from 'vue-property-decorator';

import { required } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';

import ElementService from '@/entities/element/element.service';
import { IElement } from '@/shared/model/element.model';

import { IProduit, Produit } from '@/shared/model/produit.model';
import ProduitService from './produit.service';

const validations: any = {
  produit: {
    reference: {},
    nom: {
      required,
    },
    prix: {},
  },
};

@Component({
  validations,
})
export default class ProduitUpdate extends Vue {
  @Inject('produitService') private produitService: () => ProduitService;
  @Inject('alertService') private alertService: () => AlertService;

  public produit: IProduit = new Produit();

  @Inject('elementService') private elementService: () => ElementService;

  public elements: IElement[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.produitId) {
        vm.retrieveProduit(to.params.produitId);
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
    if (this.produit.id) {
      this.produitService()
        .update(this.produit)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A Produit is updated with identifier ' + param.id;
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
      this.produitService()
        .create(this.produit)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A Produit is created with identifier ' + param.id;
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

  public retrieveProduit(produitId): void {
    this.produitService()
      .find(produitId)
      .then(res => {
        this.produit = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.elementService()
      .retrieve()
      .then(res => {
        this.elements = res.data;
      });
  }
}
