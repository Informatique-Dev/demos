import { CategoryRepository } from '../../domain/category/category.repository';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Category } from 'src/app/domain/category/models/category';

@Component({
  selector: 'app-category-page',
  templateUrl: './category-page.component.html',
  styleUrls: ['./category-page.component.scss'],
})
export class CategoryPageComponent implements OnInit {
  categoryForm!: FormGroup;
  allCategory: CategoryPageComponent[] = [];
  isAppear!: boolean;
  displayedColumns: string[] = ['id', 'name', 'version', 'statue'];
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

  ngOnInit(): void {
    this.categoryForm;
  }
  resetTheForm(): void {
    this.categoryForm.reset();
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
  //delete values
  deleteCategory(candidate: Category) {
    this.message
      .deleteConfirmation('', 'Are you sure you want to delete this candidate?')
      .subscribe((res: any) => {
        if (res.success)
          this.categoryRepository
            .delete(candidate.id)
            .subscribe((_) => this.getAllCategory());
      });
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

  changeVisibility() {
    this.isVisible = !this.isVisible;
  }
  openInputField() {
    this.isVisible = true;
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
