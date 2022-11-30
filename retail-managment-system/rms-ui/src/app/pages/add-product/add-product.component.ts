import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Product } from 'src/app/domain/product/models/product';
import { ProductRepository } from 'src/app/domain/product/product.repository';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styles: ['.add-product { width: 800px; height: 630px; }'],
})
export class AddProductComponent implements OnInit {
  productForm!: FormGroup;
  allProducts: Product[] = [];

  constructor(
    private productRepository: ProductRepository,
    private build: FormBuilder
  ) {}

  ngOnInit() {
    this.getAllProducts();
    this.productForm = this.build.group({
      name: ['', [Validators.required]],
      brand: ['', [Validators.required]],
      cashPrice: ['', [Validators.required]],
      quantity: ['', [Validators.required]],
      productCategoryDto: ['', [Validators.required]],
      modelNo: ['', [Validators.required]],
    });
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
}
