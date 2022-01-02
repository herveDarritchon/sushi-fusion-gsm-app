import { RoleEnum } from '@/shared/model/enumerations/role-enum.model';
export interface IUtilisateur {
  id?: number;
  nom?: string;
  role?: RoleEnum;
}

export class Utilisateur implements IUtilisateur {
  constructor(public id?: number, public nom?: string, public role?: RoleEnum) {}
}
