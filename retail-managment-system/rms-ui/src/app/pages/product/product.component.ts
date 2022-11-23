import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Product } from 'src/app/domain/product/models/product';
import { ProductRepository } from 'src/app/domain/product/product.repository';
import { AddProductComponent } from '../add-product/add-product.component';
import { UpdateProductComponent } from '../update-product/update-product.component';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styles: [
    '.product { min-height: 850px; } table { min-width: 1200px; min-height: 800px; }',
  ],
})
export class ProductComponent implements OnInit {
  allProducts: Product[] = [];
  productForm!: FormGroup;
  displayedColumns: string[] = [
    'id',
    'name',
    'brand',
    'price',
    'quantity',
    'update',
    'delete',
  ];

  constructor(
    private productRepository: ProductRepository,
    public dialog: MatDialog,
    private build: FormBuilder
  ) {}

  ngOnInit() {
    this.getAllProducts();
  }

  getAllProducts(): void {
    this.productRepository.getList().subscribe((result) => {
      this.allProducts = result;
    });
  }

  addProduct() {
    this.productRepository.add(this.productForm.value).subscribe(() => {
      this.getAllProducts();
    });
  }

  updateProduct() {
    this.productRepository.update(this.productForm.value).subscribe(() => {
      this.getAllProducts();
    });
  }

  fetchData(product: Product): void {
    this.resetTheForm();
    this.productForm.patchValue(product);
  }

  resetTheForm(): void {
    this.productForm.reset();
  }

  openDialog() {
    this.dialog.open(AddProductComponent);
  }
  openUpdateDialog(element: Product) {
    this.dialog.open(UpdateProductComponent, {
      data: element,
    });
  }
}
