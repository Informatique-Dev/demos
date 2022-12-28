import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CategoryRepository } from '../../domain/category/category.repository';
import { Category } from 'src/app/domain/category/models/category';
import { ConfirmDialogComponent } from 'src/app/shared/components/confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-category-page',
  templateUrl: './category-page.component.html',
  styleUrls: ['./category-page.component.scss'],
})
export class CategoryPageComponent implements OnInit {
  categoryForm!: FormGroup;
  allCategory: Category[] = [];
  displayedColumns: string[] = ['id', 'name', 'status', 'update', 'delete'];
  submit: boolean = false;
  currentCategory!: Category;

  constructor(
    private formBuilder: FormBuilder,
    private categorRepository: CategoryRepository,
    private dialog: MatDialog,
    private SnackBar: MatSnackBar
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
    this.categoryForm.markAllAsTouched();
    if (this.categoryForm.valid) {
      this.categoryForm.controls['id'].value
        ? this.updateCategory()
        : this.addCategory();
      this.categoryForm.reset();
    }
  }
  addCategory() {
    this.submit = true;
    this.categorRepository.add(this.categoryForm.value).subscribe(
      () => {
        this.getAllCategory();
        this.submit = false;
        this.SnackBar.open('Category Added Successfully', 'Close', {
          duration: 2000,
        });
      },
      () => {
        this.submit = false;
      }
    );
  }
  updateCategory(): void {
    this.submit = true;
    this.categorRepository.update(this.categoryForm.value).subscribe(
      () => {
        this.getAllCategory();
        this.submit = false;
        this.SnackBar.open('Category Updated Successfully', 'Close', {
          duration: 2000,
        });
      },
      () => {
        this.submit = false;
      }
    );
  }
  resetTheForm(): void {
    this.categoryForm.controls['id'].value
      ? this.fetchData(this.currentCategory)
      : this.clearTheForm();
  }
  openConfirmationDialog(Categori: Category) {
    let dialogRef = this.dialog.open(ConfirmDialogComponent);
    dialogRef.afterClosed().subscribe((result) => {
      if (result === 'yes') {
        this.deleteCategory(Categori);
      }
    });
  }
  deleteCategory(Categori: Category): void {
    this.categorRepository.delete(Categori.id).subscribe(() => {
      this.getAllCategory();
      this.SnackBar.open('Category Deleted Successfully', 'Close', {
        duration: 2000,
      });
    });
  }
  clearTheForm(): void {
    this.categoryForm.reset();
  }
  restartForm(): void {
    this.clearTheForm();
  }
}
