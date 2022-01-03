import { IClient } from '@/shared/model/client.model';

import { RoleEnum } from '@/shared/model/enumerations/role-enum.model';
export interface IUtilisateur {
  id?: number;
  nom?: string;
  role?: RoleEnum;
  createdDate?: Date | null;
  startDate?: Date | null;
  endDate?: Date | null;
  client?: IClient | null;
}

export class Utilisateur implements IUtilisateur {
  constructor(
    public id?: number,
    public nom?: string,
    public role?: RoleEnum,
    public createdDate?: Date | null,
    public startDate?: Date | null,
    public endDate?: Date | null,
    public client?: IClient | null
  ) {}
}
