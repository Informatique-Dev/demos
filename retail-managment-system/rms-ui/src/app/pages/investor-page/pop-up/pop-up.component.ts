import { InvestorsRepository } from './../../../domain/investors/investor.repository';
import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Investors } from 'src/app/domain/investors/models/investor';

@Component({
  selector: 'app-pop-up',
  templateUrl: './pop-up.component.html',
})
export class PopUpInvestorComponent implements OnInit {
  popupInvestorForm!: FormGroup;
  allInvestors: Investors[] = [];
  submitted: boolean = false;
  constructor(
    private formBuilder: FormBuilder,
    private investorsRepository: InvestorsRepository,
    public dialogRef: MatDialogRef<PopUpInvestorComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Investors
  ) {}
  ngOnInit(): void {
    this.investorsForm();
    this.fetchData(this.data);
  }
  investorsForm() {
    this.popupInvestorForm = this.formBuilder.group({
      id: [''],
      fullName: ['', [Validators.required]],
      nickName: ['', [Validators.required]],
      nationalId: ['', [Validators.required]],
      primaryPhoneNo: ['', [Validators.required]],
      secondaryPhoneNo: ['', [Validators.required]],
      address: ['', [Validators.required]],
      investorType: ['', [Validators.required]],
      balance: ['', [Validators.required]],
      startDate: ['', [Validators.required]],
    });
  }

  onSubmit() {
    if (this.popupInvestorForm.valid) {
      this.submitted = true;
      this.popupInvestorForm.controls['id'].value
        ? this.UpdateInvestors()
        : this.addInvestors();
      this.popupInvestorForm.reset();
    }
  }
  addInvestors() {
    this.investorsRepository.add(this.popupInvestorForm.value).subscribe(() => {
      this.dialogRef.close();
    });
  }
  UpdateInvestors(): void {
    this.investorsRepository
      .update(this.popupInvestorForm.value)
      .subscribe(() => {
        this.dialogRef.close();
      });
  }
  resetTheForm(): void {
    this.popupInvestorForm.reset();
  }
  fetchData(element: Investors): void {
    this.popupInvestorForm.patchValue(this.data);
  }
}
4;
