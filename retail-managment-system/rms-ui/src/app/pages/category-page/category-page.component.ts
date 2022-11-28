import { CategoryRepository } from '../../domain/category/category.repository';
import { Component, OnInit } from '@angular/core';
import { Category } from 'src/app/domain/category/models/category';
import { MatDialog } from '@angular/material/dialog';
import { PopUpComponent } from './pop-up/pop-up.component';
@Component({
  selector: 'app-category-page',
  templateUrl: './category-page.component.html',
})
export class CategoryPageComponent implements OnInit {
  allCategory: Category[] = [];
  isAppear!: boolean;
  displayedColumns: string[] = ['id', 'name', 'status'];
  dataimage: any;
  submit: boolean = false;
  submitted: boolean = false;

  constructor(
    private categoryRepository: CategoryRepository,
    private dialog: MatDialog
  ) {}
  ngOnInit(): void {
    this.getAllCategory();
  }
  getAllCategory(): void {
    this.categoryRepository.getList().subscribe((result: any) => {
      this.allCategory = result;
    });
  }
  openDialog(element: Category | null) {
    this.dialog.open(PopUpComponent, {
      data: element,
    });
  }
}
