import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Category } from 'src/app/domain/category/models/category';
import { Product } from 'src/app/domain/product/models/product';
import { ProductRepository } from 'src/app/domain/product/product.repository';
import { CategoryRepository } from 'src/app/domain/category/category.repository';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmDialogComponent } from '../../shared/components/confirm-dialog/confirm-dialog.component';
import { TranslateService } from '@ngx-translate/core';
import { Pagination } from 'src/app/core/models/pagination';
import { PageEvent } from '@angular/material/paginator';
import { SharedService } from 'src/app/shared/service/shared.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss'],
  encapsulation: ViewEncapsulation.None,
})
export class ProductComponent implements OnInit {
  defaultValue:number=1;
  products:Product[]=[];
  allProducts: Product[] = [];
  size: number = 10;
  page: number = 0;
  totaItem: number = 0;
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
    'addToCart'
  ];

  constructor(
    private productRepository: ProductRepository,
    private categoryRepository: CategoryRepository,
    private build: FormBuilder,
    private _snackBar: MatSnackBar,
    private dialog: MatDialog,
    private translate: TranslateService,
    private sharedService:SharedService
  ) {}

  ngOnInit() {
    this.getAllProducts();
    this.getAllTempCat();
    this.prodForm();
  }

  updateQuantity(product: Product, newQuantity: number) {
    product.orderQuantity = newQuantity;
    this.sharedService.setQuantity(newQuantity);

}

addToCart(data:Product){
  if (data.orderQuantity==undefined) {
    data.orderQuantity=1;
    this.sharedService.setQuantity(data.orderQuantity);
    this.products.push(data);
    this.sharedService.loadProducts(this.products);
  }
  else{
    this.products.push(data);
    this.sharedService.loadProducts(this.products);
  }
}

  productFilter(id : number){
     this.productRepository.filterProductsById(id).subscribe((result:any)=>{
      this.allProducts = result.data
      this.totaItem = result.pagination.itemCount;
    if(this.allProducts.length == 0){
          this._snackBar.open(
            this.translate.instant('product.no-products'),
            this.translate.instant('product.close'),
            {
              duration: 2000,
            }
          );
        }
    })
  }

  getAllProducts(): void {
    this.productRepository.getList({  page: this.page,size: this.size,}).subscribe((result) => {
      this.allProducts = result.data;
      this.totaItem = result.pagination.itemCount;
    });
  }

  onPaginationChange(event: PageEvent) {

    this.page  = event.pageIndex;
    this.size = event.pageSize;
    this.getAllProducts();
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
    this.productRepository.update(this.productForm.value).subscribe(
      () => {
        this.getAllProducts();
        this.submit = false;
        this._snackBar.open(
          this.translate.instant('product.updated-successfuly'),
          this.translate.instant('product.close'),
          {
            duration: 2000,
          }
        );
      },
      () => {
        this.submit = false;
      }
    );
  }

  addProduct() {
    this.isButtonVisible = true;
    this.submit = true;
    this.productRepository.add(this.productForm.value).subscribe(
      () => {
        this.getAllProducts();
        this.submit = false;
        this._snackBar.open(
          this.translate.instant('product.added-successfuly'),
          this.translate.instant('product.close'),
          {
            duration: 2000,
          }
        );
      },
      () => {
        this.submit = false;
      }
    );
  }

  getAllTempCat(): void {
    this.categoryRepository.getList().subscribe((result) => {
      this.categories = result.data;
    });
  }

  onSubmit() {
    this.productForm.markAllAsTouched();
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

  openConfirmationDialog(product: Product) {
    let dialogRef = this.dialog.open(ConfirmDialogComponent);
    dialogRef.afterClosed().subscribe((result) => {
      if (result === 'yes') {
        this.deleteProduct(product);
      }
    });
  }

  deleteProduct(product: Product) {
    this.productRepository.delete(product.id).subscribe(() => {
      this.getAllProducts();
      this._snackBar.open(
        this.translate.instant('product.delete-successfuly'),
        this.translate.instant('product.close'),
        {
          duration: 2000,
        }
      );
    });
  }
}
