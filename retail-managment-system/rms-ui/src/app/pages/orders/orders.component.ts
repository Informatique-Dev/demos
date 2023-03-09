import { EmployeeRepository } from './../../domain/employee/employee.repository';
import { Product } from './../../domain/product/models/product';
import { OrderRepository } from './../../domain/order/order.repository';
import { Order } from './../../domain/order/models/order';
import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TranslateService } from '@ngx-translate/core';
import { CategoryRepository } from 'src/app/domain/category/category.repository';
import { ProductRepository } from 'src/app/domain/product/product.repository';
import { Category } from 'src/app/domain/category/models/category';
import { Employee } from 'src/app/domain/employee/models/employee';
import { PageEvent } from '@angular/material/paginator';
export interface PeriodicElement {
  name: string;
  position: number;
  weight: number;
  symbol: string;
}
@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.scss'],
})
export class OrdersComponent implements OnInit {
  orderDataSource: Order[] = [];
  allCategory: Category[] = [];
  allEmployee: Employee[] = [];
  allProducts: any[] = [];
  filteredList: Product[] = [];
  displayedColumns: string[] = [
    'id',
    'date',
    'paymentType',
    'paidAmount',
    'remainingAmount',
    'installmentAmount',
    'employee',
    'customer',
  ];
  orderForm!: FormGroup;
  size: number = 10;
  page: number = 0;
  totaItem: number = 0;
  constructor(
    private orderRepository: OrderRepository,
    private categoryRepository: CategoryRepository,
    private productRepository: ProductRepository,
    private employeeRepository: EmployeeRepository,
    private build: FormBuilder,
    private snackBar: MatSnackBar,
    private dialog: MatDialog,
    private translate: TranslateService
  ) {}

  ngOnInit(): void {
    this.getAllOrders();
    this.getAllCategory();
    this.getAllEmployee();
    this.orderDataForm();
  }
  onSubmit() {
    this.orderRepository.add(this.orderForm.value).subscribe((response) => {
      console.log({ response });
    });
  }
  getAllOrders() {
    this.orderRepository
      .getList({ page: this.page, size: this.size })
      .subscribe((response) => {
        this.orderDataSource = response.data;
        this.totaItem = response.pagination.itemCount;
      });
  }
  getAllCategory() {
    this.categoryRepository.getList().subscribe((response) => {
      this.allCategory = response.data;
    });
  }
  getAllProducts(event) {
    this.productRepository
      .filterProductsById(event.value)
      .subscribe((response) => {
        this.allProducts = response.data;
      });
  }
  getAllEmployee() {
    this.employeeRepository.getList().subscribe((response) => {
      this.allEmployee = response.data;
    });
  }
  onPaginationChange(event: PageEvent) {
    this.page = event.pageIndex;
    this.size = event.pageSize;
    this.getAllEmployee();
  }
  orderDataForm() {
    this.orderForm = this.build.group({
      id: [],
      version: [],
      paymentType: [,[Validators.required]],
      paidAmount: [0,[Validators.required]],
      remainingAmount: [0],
      installmentAmount: [],
      employeeId: ['',[Validators.required]],
       customer: this.build.group({
        fullName: ['',[Validators.required]],
        nickName: [,[Validators.required]],
        customerCode: [],
        nationalId: ['', [
          Validators.required,
          Validators.pattern('^[0-9]{1,14}$'),
          Validators.minLength(14),
          Validators.maxLength(14),
        ],],
        primaryPhoneNo: ['',[Validators.required, Validators.pattern('^01[0-2,5]{1}[0-9]{8}$')]],
        secondaryPhoneNo: ['',[Validators.pattern('^01[0-2,5]{1}[0-9]{8}$')]],
        address: [''],
        trustReceiptNo: [],
      }),
      orderItems: this.build.array([],[Validators.required]),
    });
  }
  orderItemData(): FormArray {
    return this.orderForm.get('orderItems') as FormArray;
  }

  newOrderItem(): FormGroup {
    return this.build.group({
      unitPrice: [],
      quantity: [],
      category: [],
      paymentType: [],
      product: [],
    });
  }
  addNewOrderItem() {
    this.orderItemData().push(this.newOrderItem());
  }
  removeOrderItemData(empIndex: number) {
    this.orderItemData().removeAt(empIndex);
  }
  resetForm() {
    this.orderForm.reset();
  }
}
