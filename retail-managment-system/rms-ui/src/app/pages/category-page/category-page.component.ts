import { CategoryRepository } from '../../domain/category/category.repository';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Category } from 'src/app/domain/category/models/category';
import { PageEvent } from '@angular/material/paginator';
@Component({
  selector: 'app-category-page',
  templateUrl: './category-page.component.html',
})
export class CategoryPageComponent implements OnInit {
  categoryForm!: FormGroup;
  allCategory: Category[] = [];
  isAppear!: boolean;
  displayedColumns: string[] = ['id', 'name', 'status'];
  dataimage: any;
  submit: boolean = false;
  submitted: boolean = false;
  isVisible!: boolean;
  size: number = 10;
  page: number = 0;
  totalRows: number = 0;
  message: any;

  constructor(
    private formBuilder: FormBuilder,
    private categoryRepository: CategoryRepository
  ) {}

  resetTheForm(): void {
    this.categoryForm.reset();
  }
  ngOnInit(): void {
    this.categoriForm();
    this.getAllCategory();
  }
  categoriForm() {
    this.categoryForm = this.formBuilder.group({
      id: [''],
      name: [],

      status: [],
    });
  }
  addCategory() {
    this.categoryRepository.add(this.categoryForm.value).subscribe(() => {
      this.getAllCategory();
    });
  }
  UpdateCategory(): void {
    this.categoryRepository.update(this.categoryForm.value).subscribe(() => {
      this.getAllCategory();
    });
  }
  getAllCategory(): void {
    this.categoryRepository.getList().subscribe((result: any) => {
      this.allCategory = result;

      console.log(this.allCategory);
    });
  }
  fetchData(category: Category): void {
    this.resetTheForm();
    this.categoryForm.patchValue({
      id: category.id,
      name: category.name,
      status: category.status,
    });
  }
}
