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
  styleUrls: ['./category-page.component.scss'],
})
export class CategoryPageComponent implements OnInit {
  categoryForm!: FormGroup;
  allCategory: CategoryPageComponent[] = [];
  isAppear!: boolean;
  displayedColumns: string[] = ['id', 'name', 'version', 'status'];
  dataimage: any;
  submit: boolean = false;
  submitted: boolean = false;
  isVisible!: boolean;
  formBuilder: any;
  size: number = 10;
  page: number = 0;
  totalRows: number = 0;
  message: any;

  constructor(
    private FormBuilder: FormBuilder,
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
      name: ['', Validators.required],
      id: ['', [Validators.required, Validators.pattern('[0-9]{10,10}')]],
      version: [
        '',
        [
          Validators.required,
          Validators.pattern(
            /^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$/
          ),
        ],
      ],
      status: [
        '',
        [
          Validators.required,
          Validators.pattern(
            /^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$/
          ),
        ],
      ],
      // this.name=this.form.controls.name as FormControl,
      // this.id=this.form.controls.id as FormControl,
      // this.version=this.form.controls.version as FormControl,
      // this.status=this.form.controls.status as FormControl
    });
  }

  //addCategory
  addCategory() {
    this.categoryRepository.add(this.categoryForm.value).subscribe(() => {
      this.getAllCategory();
      this.changeVisibility();
    });
  }
  //update all value
  UpdateCategory() {
    this.categoryRepository.update(this.categoryForm.value).subscribe(() => {
      this.getAllCategory();
      this.changeVisibility();
    });
  }
  pageChanged(event: PageEvent): void {
    this.size = event.pageSize;
    this.page = event.pageIndex;
    this.getAllCategory();
  }
  //Get all data
  getAllCategory(): void {
    this.categoryRepository
      .getList({
        page: this.page,
        size: this.size,
      })
      .subscribe((result: any) => {
        this.allCategory = result.data;
        this.totalRows = result.pagination.itemCount;
      });
  }
  onSubmit() {
    if (this.categoryForm.valid) {
      this.submitted = true;
      this.categoryForm.controls['id'].value
        ? this.UpdateCategory()
        : this.addCategory();
      this.categoryForm.reset();
    }
  }
  changeVisibility() {
    this.isVisible = !this.isVisible;
  }
  openInputField() {
    this.isVisible = true;
  }
  appearRest() {
    this.isAppear = true;
  }
  fetchData(category: Category): void {
    this.resetTheForm();
    this.categoryForm.patchValue({
      id: category.id,
      name: category.name,
      status: category.status,
      version: category.version,
    });
  }
}
