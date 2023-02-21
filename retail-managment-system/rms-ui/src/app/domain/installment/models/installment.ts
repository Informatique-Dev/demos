import { Order } from "../../order/models/order";


export interface Installment {
     id: number;
    version: number;
    installmentAmount :number;
    paymentAmount : number;
    dueDate : Date;
    paymentDate : Date;
    status : number ; 
    order : Order   
}
