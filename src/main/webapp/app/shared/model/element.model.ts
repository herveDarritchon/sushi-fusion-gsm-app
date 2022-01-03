import { IProduit } from '@/shared/model/produit.model';

export interface IElement {
  id?: number;
  nom?: string;
  createdDate?: Date | null;
  startDate?: Date | null;
  endDate?: Date | null;
  produits?: IProduit[] | null;
}

export class Element implements IElement {
  constructor(
    public id?: number,
    public nom?: string,
    public createdDate?: Date | null,
    public startDate?: Date | null,
    public endDate?: Date | null,
    public produits?: IProduit[] | null
  ) {}
}
