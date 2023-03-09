import { Product } from "../../product/models/product";

export interface OrderItem{
  id:number;
  version:number;
  unitPrice:number;
  quantity:number;
  paymentType:number;
  product:Product;
  order: string;

}
