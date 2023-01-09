import { Investors } from '../../investors/models/investor';
export enum TransactionType {
    deposit="DEPOSIT",
    withdraw="WITHDRAW",
    profit='PROFIT'
}

export interface Transaction {
    id: number,
    version: number,
    transactionType: TransactionType,
    amount: number,
    date: string,
    investor: Investors,
}
