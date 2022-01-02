/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import ElementDetailComponent from '@/entities/element/element-details.vue';
import ElementClass from '@/entities/element/element-details.component';
import ElementService from '@/entities/element/element.service';
import router from '@/router';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Element Management Detail Component', () => {
    let wrapper: Wrapper<ElementClass>;
    let comp: ElementClass;
    let elementServiceStub: SinonStubbedInstance<ElementService>;

    beforeEach(() => {
      elementServiceStub = sinon.createStubInstance<ElementService>(ElementService);

      wrapper = shallowMount<ElementClass>(ElementDetailComponent, {
        store,
        localVue,
        router,
        provide: { elementService: () => elementServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundElement = { id: 123 };
        elementServiceStub.find.resolves(foundElement);

        // WHEN
        comp.retrieveElement(123);
        await comp.$nextTick();

        // THEN
        expect(comp.element).toBe(foundElement);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundElement = { id: 123 };
        elementServiceStub.find.resolves(foundElement);

        // WHEN
        comp.beforeRouteEnter({ params: { elementId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.element).toBe(foundElement);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        comp.previousState();
        await comp.$nextTick();

        expect(comp.$router.currentRoute.fullPath).toContain('/');
      });
    });
  });
});
