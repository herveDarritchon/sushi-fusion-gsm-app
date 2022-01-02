import { IUtilisateur } from '@/shared/model/utilisateur.model';

import { ClientEnum } from '@/shared/model/enumerations/client-enum.model';
export interface IClient {
  id?: number;
  nom?: string;
  adresse?: string | null;
  type?: ClientEnum | null;
  vendeur?: IUtilisateur | null;
}

export class Client implements IClient {
  constructor(
    public id?: number,
    public nom?: string,
    public adresse?: string | null,
    public type?: ClientEnum | null,
    public vendeur?: IUtilisateur | null
  ) {}
}
