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
  constructor(
    private router: Router,
    private productRepository: ProductRepository,
    private categoryRepository: CategoryRepository,
    private customersRepository: CustomersRepository,
    private investorsRepository: InvestorsRepository,
    private installmentsRepository : InstallmentRepositry

  ) {}
  ngOnInit() {
    this.getAllProducts();
    this.getAllCustomers();
    this.getAllCategory();
    this.getAllInvestors();
    this.getAllInstallments();
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

  openInvestors() {
    this.router.navigate(['/investor']);
  }

  openInstallment() {
    this.router.navigate(['/installment']);
  }

  getAllProducts(): void {
    this.productRepository.getList().subscribe((result) => {
      this.allProducts = result;
    });
  }
  getAllCustomers(): void {
    this.customersRepository.getList().subscribe((result) => {
      this.allCustomers = result;
    });
  }
  getAllCategory(): void {
    this.categoryRepository.getList().subscribe((result: any) => {
      this.allCategory = result;
    });
  }
  getAllInvestors(): void {
    this.investorsRepository.getList().subscribe((result: any) => {
      this.allInvestors = result;
    });
  }

  getAllInstallments(): void {
    this.installmentsRepository.getList().subscribe((result: any) => {
      this.allInstallments = result;
    });
  }
}
