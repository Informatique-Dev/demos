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
  investorType: InvestorType;
}
export enum InvestorType {
  'CONTRIBUTOR',
  'MANAGER',
}
