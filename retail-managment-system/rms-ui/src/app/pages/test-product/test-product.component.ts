import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TranslateService } from '@ngx-translate/core';
import { CategoryRepository } from 'src/app/domain/category/category.repository';
import { Category } from 'src/app/domain/category/models/category';
import { Product } from 'src/app/domain/product/models/product';
import { ProductRepository } from 'src/app/domain/product/product.repository';
import { ConfirmDialogComponent } from 'src/app/shared/components/confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-test-product',
  templateUrl: './test-product.component.html',
  styles: ['.btn {background-color: #002d40;color: white;width: 80px;height: 40px;}'
  ,'.table-button{color: #002d40;background-color: transparent;}']
})
export class TestProductComponent implements OnInit {
 allProducts:Product[]=[];
 allCategories:Category[]=[];
 pageSize:number=10;
 length:number=0;
 page:number=0;
 currentProduct!:Product;
 displayedColumns=["id","name","category","brand","price","quantity","actions"];
 productFormConntrols!:FormGroup;
 addButtonVisible:boolean=false;
  constructor(private productRepository:ProductRepository,
    private categoryRepository:CategoryRepository,
    private formBuilder:FormBuilder, private snackBar: MatSnackBar,
    private dialog: MatDialog,
    private translate: TranslateService) { }
  
  ngOnInit(): void {
    this.getAllProducts();
    this.getAllCategories();
    this.productForm();
  }
  get name(){
    return this.productFormConntrols.get('name');
  }
  get category(){
    return this.productFormConntrols.get('productCategoryDto');
  }
  get cashPrice(){
    return this.productFormConntrols.get('cashPrice');
  }
  productForm(){
    this.productFormConntrols=this.formBuilder.group({
      id: [''],
      name: ['', [Validators.required]],
      brand: [''],
      cashPrice: ['', [Validators.required]],
      quantity: [''],
      productCategoryDto: ['', [Validators.required]],
      modelNo: [''],
    })
  }
  getAllProducts(){
    return this.productRepository.getList({page:this.page,size:this.pageSize}).subscribe((data)=>{
      this.allProducts=data.data;
      this.length=data.pagination.itemCount;
    })
  }
  getAllCategories(){
    return this.categoryRepository.getList().subscribe((data)=>{
     this.allCategories=data.data;
    })
  }
  handlePageEvent(event:PageEvent){
    this.pageSize=event.pageSize;
    this.page=event.pageIndex;
    this.getAllProducts();
  }
  filterProductByCategory(id:number){
    this.productRepository.filterProductsById(id).subscribe((data)=>{
     this.allProducts=data.data;
    })
  }
  submitProduct(){
    if(this.productFormConntrols.controls["id"].value){
      this.editProduct()
    }
    else{
      this.addProduct();
    }
  }
  fillForm(product:Product){
    this.addButtonVisible=true;
    this.productFormConntrols.patchValue(product);
    this.currentProduct=product;
  }
  addProduct(){
    this.addButtonVisible=false;
    this.productRepository.add(this.productFormConntrols.value).subscribe(()=>{
      this.getAllProducts();
      this.snackBar.open(
       this.translate.instant('product.added-successfuly'),
       this.translate.instant('product.close'),
       {
         duration: 2000,
       }
     );
     })
  }
  editProduct(){
    this.addButtonVisible=true;
    this.productRepository.update(this.productFormConntrols.value).subscribe(()=>{
      this.getAllProducts();
      this.snackBar.open(
       this.translate.instant('product.updated-successfuly'),
       this.translate.instant('product.close'),
       {
         duration: 2000,
       }
     );
    })
  }
  resetForm(){
    if(this.productFormConntrols.controls["id"].value){
      this.fillForm(this.currentProduct);
    }
    else{
      this.productFormConntrols.reset();
    }
  }
  clearForm(){
    this.addButtonVisible=false;
    this.productFormConntrols.reset();
  }
  confirmDialog(product:Product){
    let dialoResult=this.dialog.open(ConfirmDialogComponent);
    dialoResult.afterClosed().subscribe((result) => {
      if (result === 'yes') {
        this.deleteProduct(product);
      }
    });
  }
  deleteProduct(product:Product){
    this.productRepository.delete(product.id).subscribe(()=>{
      this.getAllProducts();
      this.snackBar.open(
       this.translate.instant('product.deleted-successfuly'),
       this.translate.instant('product.close'),
       {
         duration: 2000,
       }
     );
    })
  }
  compareFn(category1: Category, category2: Category) {
    if (!category1 || !category2) return false;
    return category1.id === category2.id;
  }
}
