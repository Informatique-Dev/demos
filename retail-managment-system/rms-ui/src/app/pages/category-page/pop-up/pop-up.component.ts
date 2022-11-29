import { Component, Inject, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { CategoryRepository } from 'src/app/domain/category/category.repository';
import { Category } from 'src/app/domain/category/models/category';
@Component({
  selector: 'app-pop-up',
  templateUrl: './pop-up.component.html',
})
export class PopUpComponent implements OnInit {
  popupForm!: FormGroup;
  allCategory: Category[] = [];
  submitted: boolean = false;
  constructor(
    private formBuilder: FormBuilder,
    private categorRepository: CategoryRepository,
    public dialogRef: MatDialogRef<PopUpComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Category
  ) {}
  ngOnInit(): void {
    this.categoriForm();
    this.getAllCategory();
    this.fetchData(this.data);
  }
  categoriForm() {
    this.popupForm = this.formBuilder.group({
      id: [''],
      name: ['', [Validators.required]],
      status: ['', [Validators.required]],
    });
  }
  getAllCategory(): void {
    this.categorRepository.getList().subscribe((result: any) => {
      this.allCategory = result;
    });
  }
  onSubmit() {
    if (this.popupForm.valid) {
      this.submitted = true;
      this.popupForm.controls['id'].value
        ? this.UpdateCategory()
        : this.addCategory();
      this.popupForm.reset();
    }
  }
  addCategory() {
    this.categorRepository.add(this.popupForm.value).subscribe(() => {
      this.dialogRef.close();
      this.getAllCategory();
    });
  }
  UpdateCategory(): void {
    this.categorRepository.update(this.popupForm.value).subscribe(() => {
      this.dialogRef.close();
      this.getAllCategory();
    });
  }
  resetTheForm(): void {
    this.popupForm.reset();
  }
  fetchData(element: Category): void {
    this.popupForm.patchValue(this.data);
  }
}
4;
