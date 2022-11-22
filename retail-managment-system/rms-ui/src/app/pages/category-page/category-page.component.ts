import { FormGroup } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-category-page',
  templateUrl: './category-page.component.html',
  styleUrls: ['./category-page.component.scss'],
})
export class CategoryPageComponent implements OnInit {
  categoryForm!: FormGroup;
  allCategory: Category[] = [];
  isAppear!: boolean;
  pictureChanged: boolean = false;
  displayedColumns: string[] = ['id', 'name', 'version', 'statue'];
  dataimage: any;
  submit: boolean = false;
  submitted: boolean = false;
  isVisible: boolean;
  constructor() {}

  ngOnInit(): void {}
  resetTheForm(): void {
    this.categoryForm.reset();
    this.pictureChanged = false;
  }
  fetchData(candidate: Category): void {
    this.resetTheForm();
    this.categoryForm.patchValue({
      id: candidate.id,
      name: candidate.name,
      email: candidate.email,
      phone: candidate.phone,
      documentId: candidate.documentId
    });

  onSubmit() {}

  appearRest() {
    this.isAppear = true;
  }
}
