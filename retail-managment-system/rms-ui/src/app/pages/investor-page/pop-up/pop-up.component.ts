import { InvestorsRepository } from './../../../domain/investors/investor.repository';
import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Investors } from 'src/app/domain/investors/models/investor';

@Component({
  selector: 'app-pop-up',
  templateUrl: './pop-up.component.html',
  styles: [
    '.manageinvestors{min-width:200px;min-height:300px;margin-top:10px} .btn{background-color:#002d40;color:white;width:80px}',
  ],
})
export class PopUpInvestorComponent implements OnInit {
  popupInvestorForm!: FormGroup;
  allInvestors: Investors[] = [];
  submitted: boolean = false;
  click: boolean = false;
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
      nickName: [''],
      nationalId: ['', [Validators.required]],
      primaryPhoneNo: ['', [Validators.required]],
      secondaryPhoneNo: [''],
      address: [''],
      investorType: ['', [Validators.required]],
      balance: [''],
      startDate: [''],
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
    this.investorsRepository
      .add(this.popupInvestorForm.value)
      .subscribe(() => {});
  }

  UpdateInvestors(): void {
    this.investorsRepository
      .update(this.popupInvestorForm.value)
      .subscribe(() => {
        this.dialogRef.close();
      });
  }
  onButtonClick() {
    this.click = !this.click;
  }
  resetTheForm(): void {
    this.popupInvestorForm.reset();
  }
  fetchData(element: Investors): void {
    this.popupInvestorForm.patchValue(this.data);
  }
}
