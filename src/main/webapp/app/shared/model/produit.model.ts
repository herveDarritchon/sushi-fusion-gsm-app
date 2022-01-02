import { IElement } from '@/shared/model/element.model';

export interface IProduit {
  id?: number;
  reference?: string | null;
  nom?: string;
  prix?: number | null;
  ingredient?: IElement | null;
}

export class Produit implements IProduit {
  constructor(
    public id?: number,
    public reference?: string | null,
    public nom?: string,
    public prix?: number | null,
    public ingredient?: IElement | null
  ) {}
}
