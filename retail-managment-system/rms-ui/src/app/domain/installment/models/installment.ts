import { Order } from "./order";

export interface Installment {
     id: number;
    version: number;
    installmentAmount :number;
    paymentAmount : number;
    dueDate : string;
    paymentDate : string;
    status : number ; 
    orderDTO : Order   
}
