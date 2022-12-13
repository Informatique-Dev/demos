import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CategoryRepository } from '../../domain/category/category.repository';
import { Category } from 'src/app/domain/category/models/category';
@Component({
  selector: 'app-category-page',
  templateUrl: './category-page.component.html',
  styles: [
    ' .content-size{width:90%} mat-icon{ font-size: 29px;} .btn {background-color: #002d40; color: white}',
  ],
})
export class CategoryPageComponent implements OnInit {
  categoryForm!: FormGroup;
  allCategory: Category[] = [];
  displayedColumns: string[] = ['id', 'name', 'status', 'update', 'delete'];
  submit: boolean = false;
  currentCategory!: Category;

  constructor(
    private formBuilder: FormBuilder,
    private categorRepository: CategoryRepository
  ) {}
  ngOnInit(): void {
    this.getAllCategory();
    this.form();
  }
  form() {
    this.categoryForm = this.formBuilder.group({
      id: [''],
      name: ['', [Validators.required]],
      status: ['', [Validators.required]],
    });
  }

  fetchData(category: Category): void {
    this.categoryForm.get('id')?.setValue(category.id);
    this.categoryForm.get('name')?.setValue(category.name);
    this.categoryForm.get('status')?.setValue(category.status.toString());
    this.currentCategory = category;
  }
  getAllCategory(): void {
    this.categorRepository.getList().subscribe((result: any) => {
      this.allCategory = result;
    });
  }
  onSubmit() {
    if (this.categoryForm.valid) {
      this.categoryForm.controls['id'].value
        ? this.UpdateCategory()
        : this.addCategory();
      this.categoryForm.reset();
    }
  }
  addCategory() {
    this.submit = true;
    this.categorRepository.add(this.categoryForm.value).subscribe(() => {
      this.getAllCategory();
      this.submit = false;
    });
  }
  UpdateCategory(): void {
    this.submit = true;
    this.categorRepository.update(this.categoryForm.value).subscribe(() => {
      this.getAllCategory();
      this.submit = false;
    });
  }
  resetTheForm(): void {
    this.categoryForm.controls['id'].value
      ? this.fetchData(this.currentCategory)
      : this.clearTheForm();
  }
  DeleteCategory(Categori: Category): void {
    this.categorRepository.delete(Categori.id).subscribe(() => {
      this.getAllCategory();
    });
  }
  clearTheForm(): void {
    this.categoryForm.reset();
  }
  restartForm(): void {
    this.clearTheForm();
  }
}
