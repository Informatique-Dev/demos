import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { CategoryRepository } from 'src/app/domain/category/category.repository';
import { Category } from 'src/app/domain/category/models/category';
@Component({
  selector: 'app-pop-up',
  templateUrl: './pop-up.component.html',
  styles: ['.btn{background-color:#002d40;color:white;width:80px;}'],
})
export class PopUpComponent implements OnInit {
  popupForm!: FormGroup;
  allCategory: Category[] = [];
  submit: boolean = false;
  submitted: boolean = false;
  constructor(
    private formBuilder: FormBuilder,
    private categorRepository: CategoryRepository,
    public dialogRef: MatDialogRef<PopUpComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Category
  ) {}
  ngOnInit(): void {
    this.categoriForm();

    this.fetchData(this.data);
  }
  categoriForm() {
    this.popupForm = this.formBuilder.group({
      id: [''],
      name: ['', [Validators.required]],
      status: ['', [Validators.required]],
    });
  }

  onSubmit() {
    if (this.popupForm.valid) {
      this.popupForm.controls['id'].value
        ? this.UpdateCategory()
        : this.addCategory();
      this.popupForm.reset();
    }
  }
  addCategory() {
    this.submit = true;
    this.categorRepository.add(this.popupForm.value).subscribe(() => {
      this.submit = false;
    });
  }
  UpdateCategory(): void {
    this.submit = true;
    this.categorRepository.update(this.popupForm.value).subscribe(() => {
      this.submit = false;
      this.dialogRef.close();
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
