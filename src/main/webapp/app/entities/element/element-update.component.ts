import { Component, Vue, Inject } from 'vue-property-decorator';

import { required } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';

import ProduitService from '@/entities/produit/produit.service';
import { IProduit } from '@/shared/model/produit.model';

import { IElement, Element } from '@/shared/model/element.model';
import ElementService from './element.service';

const validations: any = {
  element: {
    nom: {
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
export default class ElementUpdate extends Vue {
  @Inject('elementService') private elementService: () => ElementService;
  @Inject('alertService') private alertService: () => AlertService;

  public element: IElement = new Element();

  @Inject('produitService') private produitService: () => ProduitService;

  public produits: IProduit[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.elementId) {
        vm.retrieveElement(to.params.elementId);
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
    this.element.produits = [];
  }

  public save(): void {
    this.isSaving = true;
    if (this.element.id) {
      this.elementService()
        .update(this.element)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A Element is updated with identifier ' + param.id;
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
      this.elementService()
        .create(this.element)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A Element is created with identifier ' + param.id;
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

  public retrieveElement(elementId): void {
    this.elementService()
      .find(elementId)
      .then(res => {
        this.element = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.produitService()
      .retrieve()
      .then(res => {
        this.produits = res.data;
      });
  }

  public getSelected(selectedVals, option): any {
    if (selectedVals) {
      return selectedVals.find(value => option.id === value.id) ?? option;
    }
    return option;
  }
}
