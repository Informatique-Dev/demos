import { Customers } from "../../customers/models/customers";

export interface Order {
    id: number;
    version: number
    orderDate: string;
    paymentType: number
    paidAmount: number;
    remainingAmount: number
    installmentAmount: number
    customer : Customers
}
