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
  investorType: string;
}
export enum InvestorTypes {
  CONTRIBUTOR = 'CONTRIBUTOR',
  MANAGER = 'MANAGER',
}
