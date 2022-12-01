import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Product } from 'src/app/domain/product/models/product';
import { ProductRepository } from 'src/app/domain/product/product.repository';
import { Category } from 'src/app/domain/category/models/category';
import { CategoryRepository } from 'src/app/domain/category/category.repository';

@Component({
  selector: 'app-manage-product',
  templateUrl: './manage-product.component.html',
  styles: ['.update-product  { min-width: 250px; min-height: 430px; }'],
})
export class ManageProductComponent implements OnInit {
  productForm!: FormGroup;
  allProducts: Product[] = [];
  allCats: Category[] = [];
  submit: boolean = false;
  submitted: boolean = false;
  constructor(
    private categoryRepository: CategoryRepository,
    private productRepository: ProductRepository,
    private build: FormBuilder,
    public dialogRef: MatDialogRef<ManageProductComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Product
  ) {}

  ngOnInit() {
    this.getAllTempCat();
    this.prodForm();
    this.fetchData();
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

  fetchData(): void {
    this.productForm.patchValue(this.data);
  }
  updateProduct() {
    this.submit = true;
    this.productRepository.update(this.productForm.value).subscribe(() => {
      this.getAllProducts();
      this.submit = false;
      this.dialogRef.close();
    });
  }
  resetTheForm(): void {
    this.productForm.reset();
  }
  addProduct() {
    this.submit = true;
    this.productRepository.add(this.productForm.value).subscribe(() => {
      this.submit = false;
    });
    this.getAllProducts();
  }

  getAllTempCat(): void {
    this.categoryRepository.getList().subscribe((result) => {
      this.allCats = result;
    });
  }

  onSubmit() {
    if (this.productForm.valid) {
      this.submitted = true;
      this.productForm.controls['id'].value
        ? this.updateProduct()
        : this.addProduct();
      this.productForm.reset();
    }
  }

  resetForm() {
    this.productForm.reset();
  }

  compareFn(a: Category, b: Category) {
    if (!a || !b) return false;
    return a.id === b.id;
  }
}
