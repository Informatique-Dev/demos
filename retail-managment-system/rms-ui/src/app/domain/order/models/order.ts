import { Customers } from "../../customers/models/customers";
import { Employee } from "../../employee/models/employee";
import { Installment } from "../../installment/models/installment";
import { orderItem } from "./orderItem";
import { paymentEnum } from "./paymentEnum";

export interface Order {
    id: number;
    version: number;
    orderDate: Date;
    paymentType: paymentEnum;
    paidAmount: number;
    remainingAmount: number;
    installmentAmount?: Installment;
    customer : Customers;
    totalPrice:number;
    orderItems:orderItem;
    employee:Employee;
}
