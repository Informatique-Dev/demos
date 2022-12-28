export enum transactionType {
    deposit="Deposit",
    withdraw="Withdraw",
    profit='Profit'
}

export interface Transaction {
    id: number,
    version: number,
    transactionType: transactionType[],
    amount: number,
    date: string,
}
