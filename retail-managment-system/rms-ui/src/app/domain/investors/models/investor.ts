export interface Investors {
  id: number;
  fullName: string;
  nickName: string;
  nationalId: string;
  primaryPhoneNo: number;
  secondaryPhoneNo: number;
  address: string;
  balance: number;
  startDate: Date;
  investorType: InvestorTypes;
}
export enum InvestorTypes {
  'CONTRIBUTOR',
  'MANAGER',
}
