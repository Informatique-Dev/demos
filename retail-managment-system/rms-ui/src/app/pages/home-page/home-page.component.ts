import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CategoryRepository } from 'src/app/domain/category/category.repository';
import { Category } from 'src/app/domain/category/models/category';
import { CustomersRepository } from 'src/app/domain/customers/customers.repository';
import { Customers } from 'src/app/domain/customers/models/customers';
import { InstallmentRepositry } from 'src/app/domain/installment/installment.repositry';
import { Installment } from 'src/app/domain/installment/models/installment';
import { InvestorsRepository } from 'src/app/domain/investors/investor.repository';
import { Investors } from 'src/app/domain/investors/models/investor';
import { Product } from 'src/app/domain/product/models/product';
import { ProductRepository } from 'src/app/domain/product/product.repository';
import { Profit } from 'src/app/domain/profit/models/profit';
import { RepositoryService } from 'src/app/domain/profit/repository.repository';
import { EmployeeRepository } from 'src/app/domain/employee/employee.repository';
import { Employee } from 'src/app/domain/employee/models/employee';
import { Transaction } from 'src/app/domain/transaction/models/transaction';
import { TransactionRepository } from 'src/app/domain/transaction/transaction.repository';
import { Supplier } from 'src/app/domain/supplier/models/supplier.model';
import { SupplierRepository } from 'src/app/domain/supplier/supplier.repository';
import { Store } from 'src/app/domain/store/models/store';
import { storeRepository } from './../../domain/store/store.repository';


@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss'],
})
export class HomePageComponent implements OnInit {
  allProducts: Product[] = [];
  allCustomers: Customers[] = [];
  allCategory: Category[] = [];
  allInvestors: Investors[] = [];
  allInstallments: Installment[] = [];
  allTransactions: Transaction[] = []
  allProfits:Profit[]=[];
  allEmployee:Employee[]=[];
  allSuppliers:Supplier[]=[];
  allStores:Store[]=[]
  constructor(
    private router: Router,
    private productRepository: ProductRepository,
    private categoryRepository: CategoryRepository,
    private customersRepository: CustomersRepository,
    private investorsRepository: InvestorsRepository,
    private installmentsRepository : InstallmentRepositry,
    private RepositoryService:RepositoryService,
    private employeeRepository:EmployeeRepository,
    private transactionRepository: TransactionRepository,
    private supplierRepository: SupplierRepository,
    private storeRepository:storeRepository


  ) {}
  ngOnInit() {
    this.getAllProducts();
    this.getAllCustomers();
    this.getAllCategory();
    this.getAllInvestors();
    this.getAllInstallments();
    this.getAllProfits();
    this.getAllTransactions();
    this.getAllEmployee();
    this.getAllSupplier();
  }

  openProducts() {
    this.router.navigate(['/product']);
  }

  openCategory() {
    this.router.navigate(['/category']);
  }

  openCustomers() {
    this.router.navigate(['/customers']);
  }

  openOrder(){
    this.router.navigate(['/order'])
  }

  openInvestors() {
    this.router.navigate(['/investor']);
  }
  openProfits() {
    this.router.navigate(['/profits']);
  }

  openInstallment() {
    this.router.navigate(['/installment']);
  }

  openTransaction() {
    this.router.navigate(['/transaction']);
  }

  openEmployee() {
    this.router.navigate(['/employee']);
  }
  openSupplier() {
    this.router.navigate(['/supplier']);
  }

  openStore(){
    this.router.navigate(['/store'])
  }

  getAllProducts(): void {
    this.productRepository.getList().subscribe((result) => {
      this.allProducts = result.data;
    });
  }
  getAllCustomers(): void {
    this.customersRepository.getList().subscribe((result:any) => {
      this.allCustomers = result;
    });
  }
  getAllCategory(): void {
    this.categoryRepository.getList().subscribe((result) => {
      this.allCategory = result.data;
    });
  }
  getAllInvestors(): void {
    this.investorsRepository.getList().subscribe((result: any) => {
      this.allInvestors = result;
    });
  }


  getAllInstallments(): void {
    this.installmentsRepository.getList().subscribe((result) => {
      this.allInstallments = result.data;

    });
  }

  getAllProfits(): void {
    this.RepositoryService.getList().subscribe((result: any) => {
      this.allProfits = result;

    });
  }

  getAllEmployee(): void {
    this.employeeRepository.getList().subscribe((result) => {
      this.allEmployee = result.data;

    });
  }
  getAllTransactions():void{
    this.transactionRepository.getList().subscribe((result)=>{
      this.allTransactions = result.data
    })
  }
  getAllSupplier():void{
    this.supplierRepository.getList().subscribe((result)=>{
      this.allSuppliers = result.data
    })
  }
}
