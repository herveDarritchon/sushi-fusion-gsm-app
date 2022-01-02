import { IProduit } from '@/shared/model/produit.model';

export interface IElement {
  id?: number;
  nom?: string;
  produits?: IProduit[] | null;
}

export class Element implements IElement {
  constructor(public id?: number, public nom?: string, public produits?: IProduit[] | null) {}
}
