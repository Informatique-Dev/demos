import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TranslateService } from '@ngx-translate/core';
import { CustomersRepository } from 'src/app/domain/customers/customers.repository';
import { Customers } from 'src/app/domain/customers/models/customers';
import { EmployeeRepository } from 'src/app/domain/employee/employee.repository';
import { Employee } from 'src/app/domain/employee/models/employee';
import { Order } from 'src/app/domain/order/models/order';
import { orderItem } from 'src/app/domain/order/models/orderItem';
import { paymentEnum } from 'src/app/domain/order/models/paymentEnum';
import { OrderRepository } from 'src/app/domain/order/order.repository';
import { Product } from 'src/app/domain/product/models/product';
import { SharedService } from 'src/app/shared/service/shared.service';
@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.scss']
})
export class OrderComponent implements OnInit {
  orderProducts!:Product[];
  orderForm!:FormGroup;
  prroducts!:Product[];
  payment=paymentEnum;
  paymentType:String[] = [];
  showInstallment:boolean=false;
  orderQuantity!:number;
  orderDto:Order[]=[];
  orderItem:orderItem[]=[];
  employees: Employee[] = [];
  customers:Customers[]=[];
  constructor(private customersRepository:CustomersRepository ,private employeeRepository:EmployeeRepository,private orderRepository:OrderRepository,private sharedService:SharedService,private build: FormBuilder,private translate: TranslateService) {

  }

  ngOnInit(): void {
    this.addOrderForm();
    this.getProduct();
    this.getPaymentType();
    this.getOrderQuantity();
    this.getAllEmployees();
    this.getAllCustomers();

    }

  getOrderQuantity(){
    this.orderQuantity = this.sharedService.getQuantity();
  }

  getPaymentType(){
    this.paymentType = Object.keys(this.payment);
  }
  showInstallmentField(data){
    if(data=='INSTALLMENTS'){
      this.showInstallment=true;
    }
    else if(data=='CASH'){
      this.showInstallment=false;

    }

  }

  getProduct(){
  this.sharedService.getProducts().subscribe(products => {
    this.orderProducts=products;
    this.orderProducts.map(product=>{
      this.orderItem.push({
        id:product.id,
        unitPrice:product.cashPrice,
        quantity:product.orderQuantity,
        product:product.id
      });
    });

    });
  }

  addOrderForm() {
    this.orderForm = this.build.group({
      orderDate: ['',[Validators.required]],
      paidAmount: ['',[Validators.required]],
      remainingAmount: ['',[Validators.required]],
      totalPrice: ['',[Validators.required]],
      paymentType: ['',[Validators.required]],
      employee:{
        id:['']
      },
      customer:{
        id:['']
      },
      installments:['']
    });
  }

getAllEmployees(): void {
  this.employeeRepository.getList().subscribe((result) => {
    this.employees = result.data;
  });
}

getAllCustomers(): void {
  this.customersRepository.getList().subscribe((result:any) => {
    this.customers = result;
  });
}
  addOrder() {
    this.orderForm.markAllAsTouched();
    let orderData = this.orderForm.value;
    orderData.orderItem = this.orderItem;
    if (this.orderForm.valid){
    this.orderRepository.add(orderData).subscribe();
    }
  }
}
