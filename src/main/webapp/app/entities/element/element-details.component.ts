import { Component, Vue, Inject } from 'vue-property-decorator';

import { IElement } from '@/shared/model/element.model';
import ElementService from './element.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class ElementDetails extends Vue {
  @Inject('elementService') private elementService: () => ElementService;
  @Inject('alertService') private alertService: () => AlertService;

  public element: IElement = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.elementId) {
        vm.retrieveElement(to.params.elementId);
      }
    });
  }

  public retrieveElement(elementId) {
    this.elementService()
      .find(elementId)
      .then(res => {
        this.element = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
