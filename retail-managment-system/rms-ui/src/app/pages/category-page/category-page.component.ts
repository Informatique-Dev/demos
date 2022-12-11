import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CategoryRepository } from '../../domain/category/category.repository';
import { Category } from 'src/app/domain/category/models/category';
@Component({
  selector: 'app-category-page',
  templateUrl: './category-page.component.html',
  styles: [
    ' .content-size{width:90%} mat-icon{ font-size: 29px;} .btn {background-color: #002d40; color: white;}.test{ width: 46%}',
  ],
})
export class CategoryPageComponent implements OnInit {
  categoryform!: FormGroup;
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
    this.categoriForm();
  }
  categoriForm() {
    this.categoryform = this.formBuilder.group({
      id: [''],
      name: ['', [Validators.required]],
      status: ['', [Validators.required]],
    });
  }

  fetchData(category: Category): void {
    this.categoryform.patchValue(category);
    this.currentCategory = category;
  }
  getAllCategory(): void {
    this.categorRepository.getList().subscribe((result: any) => {
      this.allCategory = result;
    });
  }
  onSubmit() {
    if (this.categoryform.valid) {
      this.categoryform.controls['id'].value
        ? this.UpdateCategory()
        : this.addCategory();
      this.categoryform.reset();
    }
  }
  addCategory() {
    this.submit = true;
    this.categorRepository.add(this.categoryform.value).subscribe(() => {
      this.getAllCategory();
      this.submit = false;
    });
  }
  UpdateCategory(): void {
    this.submit = true;
    this.categorRepository.update(this.categoryform.value).subscribe(() => {
      this.getAllCategory();
      this.submit = false;
    });
  }
  resetTheForm(): void {
    this.categoryform.controls['id'].value
      ? this.fetchData(this.currentCategory)
      : this.clearTheForm();
  }
  DeleteCategory(Categori: Category): void {
    this.categorRepository.delete(Categori.id).subscribe(() => {
      this.getAllCategory();
    });
  }
  clearTheForm(): void {
    this.categoryform.reset();
  }
  restartForm(): void {
    this.clearTheForm();
  }
}
