import { Employee } from '../../employee/models/employee';
import { Customers } from "../../customers/models/customers";
import { OrderItem } from './order-item';

export interface Order {
    id: number;
    version: number
    orderDate: string;
    paymentType: number;
    paidAmount: number;
    remainingAmount: number;
    installmentAmount: number;
    customer : Customers;
    employee:Employee;
    orderItems:OrderItem[];
}


