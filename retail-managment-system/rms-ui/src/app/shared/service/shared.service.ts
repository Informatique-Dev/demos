import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Product } from 'src/app/domain/product/models/product';
@Injectable({
  providedIn: 'root'
})
export class SharedService {
private availableProduct = new BehaviorSubject<Product[]>([]);
private orderQuantity :number = 1;
  constructor() { }

  loadProducts(product:Product[]){
    this.availableProduct.next(product);

  }
  getProducts():Observable<Product[]>{
    return this.availableProduct.asObservable();
  }
  getQuantity(): number {
    return this.orderQuantity;
  }

  setQuantity(newQuantity: number): void {
    if(newQuantity==null){
      this.orderQuantity =1;
    }
    else{
      this.orderQuantity = newQuantity;

    }
  }

}
