import { IElement } from '@/shared/model/element.model';

export interface IProduit {
  id?: number;
  reference?: string | null;
  nom?: string;
  prix?: number | null;
  createdDate?: Date | null;
  startDate?: Date | null;
  endDate?: Date | null;
  ingredients?: IElement[] | null;
}

export class Produit implements IProduit {
  constructor(
    public id?: number,
    public reference?: string | null,
    public nom?: string,
    public prix?: number | null,
    public createdDate?: Date | null,
    public startDate?: Date | null,
    public endDate?: Date | null,
    public ingredients?: IElement[] | null
  ) {}
}
