import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Product } from 'src/app/domain/product/models/product';
import { ProductRepository } from 'src/app/domain/product/product.repository';
import { ManageProductComponent } from './manage-product/manage-product.component';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styles: [
    '.product { min-height: auto; } table { min-width: 1200px; min-height: auto; } .btn {background-color: #002d40; color: white;}',
  ],
})
export class ProductComponent implements OnInit {
  allProducts: Product[] = [];
  productForm!: FormGroup;
  displayedColumns: string[] = [
    'id',
    'name',
    'category',
    'brand',
    'price',
    'quantity',
    'update',
    'delete',
  ];

  constructor(
    private productRepository: ProductRepository,
    public dialog: MatDialog
  ) {}

  ngOnInit() {
    this.getAllProducts();
  }

  getAllProducts(): void {
    this.productRepository.getList().subscribe((result) => {
      this.allProducts = result;
    });
  }

  openDialog(element: Product | null) {
    const dialogRef = this.dialog
      .open(ManageProductComponent, {
        data: element,
      })
      .afterClosed()
      .subscribe(() => {
        this.getAllProducts();
      });
  }
}
