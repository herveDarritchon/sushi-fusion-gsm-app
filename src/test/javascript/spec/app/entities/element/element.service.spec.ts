/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import { DATE_FORMAT } from '@/shared/date/filters';
import ElementService from '@/entities/element/element.service';
import { Element } from '@/shared/model/element.model';

const error = {
  response: {
    status: null,
    data: {
      type: null,
    },
  },
};

const axiosStub = {
  get: sinon.stub(axios, 'get'),
  post: sinon.stub(axios, 'post'),
  put: sinon.stub(axios, 'put'),
  patch: sinon.stub(axios, 'patch'),
  delete: sinon.stub(axios, 'delete'),
};

describe('Service Tests', () => {
  describe('Element Service', () => {
    let service: ElementService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new ElementService();
      currentDate = new Date();
      elemDefault = new Element(123, 'AAAAAAA', currentDate, currentDate, currentDate);
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            createdDate: dayjs(currentDate).format(DATE_FORMAT),
            startDate: dayjs(currentDate).format(DATE_FORMAT),
            endDate: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault
        );
        axiosStub.get.resolves({ data: returnedFromService });

        return service.find(123).then(res => {
          expect(res).toMatchObject(elemDefault);
        });
      });

      it('should not find an element', async () => {
        axiosStub.get.rejects(error);
        return service
          .find(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should create a Element', async () => {
        const returnedFromService = Object.assign(
          {
            id: 123,
            createdDate: dayjs(currentDate).format(DATE_FORMAT),
            startDate: dayjs(currentDate).format(DATE_FORMAT),
            endDate: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            createdDate: currentDate,
            startDate: currentDate,
            endDate: currentDate,
          },
          returnedFromService
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a Element', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a Element', async () => {
        const returnedFromService = Object.assign(
          {
            nom: 'BBBBBB',
            createdDate: dayjs(currentDate).format(DATE_FORMAT),
            startDate: dayjs(currentDate).format(DATE_FORMAT),
            endDate: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createdDate: currentDate,
            startDate: currentDate,
            endDate: currentDate,
          },
          returnedFromService
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a Element', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a Element', async () => {
        const patchObject = Object.assign(
          {
            nom: 'BBBBBB',
            startDate: dayjs(currentDate).format(DATE_FORMAT),
            endDate: dayjs(currentDate).format(DATE_FORMAT),
          },
          new Element()
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            createdDate: currentDate,
            startDate: currentDate,
            endDate: currentDate,
          },
          returnedFromService
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a Element', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of Element', async () => {
        const returnedFromService = Object.assign(
          {
            nom: 'BBBBBB',
            createdDate: dayjs(currentDate).format(DATE_FORMAT),
            startDate: dayjs(currentDate).format(DATE_FORMAT),
            endDate: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            createdDate: currentDate,
            startDate: currentDate,
            endDate: currentDate,
          },
          returnedFromService
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of Element', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a Element', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a Element', async () => {
        axiosStub.delete.rejects(error);

        return service
          .delete(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });
    });
  });
});
