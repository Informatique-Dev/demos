import { Investors } from '../../investors/models/investor';
export enum transactionType {
    deposit="DEPOSIT",
    withdraw="WITHDRAW",
    profit='PROFIT'
}

export interface Transaction {
    id: number,
    version: number,
    transactionType: transactionType,
    amount: number,
    date: string,
    investor: Investors,
}
