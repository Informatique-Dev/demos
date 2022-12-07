import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Category } from 'src/app/domain/category/models/category';
import { Product } from 'src/app/domain/product/models/product';
import { ProductRepository } from 'src/app/domain/product/product.repository';
import { CategoryRepository } from 'src/app/domain/category/category.repository';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss'],
})
export class ProductComponent implements OnInit {
  allProducts: Product[] = [];
  productForm!: FormGroup;
  categories: Category[] = [];
  submit: boolean = false;
  currentData!: Product;
  isButtonVisible: boolean = true;
  displayedColumns: string[] = [
    'id',
    'name',
    'category',
    'brand',
    'price',
    'quantity',
    'actions',
  ];

  constructor(
    private productRepository: ProductRepository,
    private categoryRepository: CategoryRepository,
    private build: FormBuilder
  ) {}

  ngOnInit() {
    this.getAllProducts();
    this.getAllTempCat();
    this.prodForm();
  }

  getAllProducts(): void {
    this.productRepository.getList().subscribe((result) => {
      this.allProducts = result;
    });
  }

  prodForm() {
    this.productForm = this.build.group({
      id: [''],
      name: ['', [Validators.required]],
      brand: [''],
      cashPrice: ['', [Validators.required]],
      quantity: [''],
      productCategoryDto: ['', [Validators.required]],
      modelNo: [''],
    });
  }

  fetchData(product: Product): void {
    this.isButtonVisible = false;
    this.productForm.patchValue(product);
    this.currentData = product;
  }

  updateProduct() {
    this.isButtonVisible = true;
    this.submit = true;
    this.productRepository.update(this.productForm.value).subscribe(() => {
      this.getAllProducts();
      this.submit = false;
    });
  }

  addProduct() {
    this.isButtonVisible = true;
    this.submit = true;
    this.productRepository.add(this.productForm.value).subscribe(() => {
      this.getAllProducts();
      this.submit = false;
    });
  }

  getAllTempCat(): void {
    this.categoryRepository.getList().subscribe((result) => {
      this.categories = result;
    });
  }

  onSubmit() {
    if (this.productForm.valid) {
      this.productForm.controls['id'].value
        ? this.updateProduct()
        : this.addProduct();
      this.productForm.reset();
    }
  }

  resetForm() {
    this.productForm.controls['id'].value
      ? this.fetchData(this.currentData)
      : this.productForm.reset();
  }
  compareFn(a: Category, b: Category) {
    if (!a || !b) return false;
    return a.id === b.id;
  }

  restartForm(): void {
    this.productForm.reset();
  }

  deleteProduct(product: Product) {
    this.productRepository.delete(product.id).subscribe(() => {
      this.getAllProducts();
    });
  }
}
