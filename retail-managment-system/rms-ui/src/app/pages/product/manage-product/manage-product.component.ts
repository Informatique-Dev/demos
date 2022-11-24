import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Product } from 'src/app/domain/product/models/product';
import { ProductRepository } from 'src/app/domain/product/product.repository';
import { temporaryCat } from 'src/app/domain/temporary cat/models/temporary-cat';
import { temporaryCatRepository } from 'src/app/domain/temporary cat/temporaryCat.repository';

@Component({
  selector: 'app-manage-product',
  templateUrl: './manage-product.component.html',
  styles: ['.update-product  { width: 800px; height: 630px; }'],
})
export class ManageProductComponent implements OnInit {
  productForm!: FormGroup;
  allProducts: Product[] = [];
  allCats: temporaryCat[] = [];
  submit: boolean = false;
  submitted: boolean = false;
  constructor(
    private temporaryCatRepository: temporaryCatRepository,
    private productRepository: ProductRepository,
    private build: FormBuilder,
    public dialogRef: MatDialogRef<ManageProductComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Product
  ) {}

  ngOnInit() {
    this.getAllProducts();
    this.getAllTempCat();
    this.productForm = this.build.group({
      id: [''],
      name: ['', [Validators.required]],
      brand: [''],
      cashPrice: ['', [Validators.required]],
      quantity: [''],
      productCategoryDto: [''],
      modelNo: [''],
    });
    this.fetchData();
  }
  getAllProducts(): void {
    this.productRepository.getList().subscribe((result) => {
      this.allProducts = result;
    });
  }
  fetchData(): void {
    this.productForm.patchValue(this.data);
  }
  updateProduct() {
    console.log(this.productForm.value);
    this.productRepository.update(this.productForm.value).subscribe(() => {
      this.getAllProducts();
      this.getAllTempCat();
    });
  }
  resetTheForm(): void {
    this.productForm.reset();
  }
  addProduct() {
    this.productRepository.add(this.productForm.value).subscribe(() => {
      this.getAllProducts();
    });
  }

  getAllTempCat(): void {
    this.temporaryCatRepository.getList().subscribe((result) => {
      console.log(result);
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
}
