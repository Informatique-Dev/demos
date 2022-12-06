import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { InvestorsRepository } from 'src/app/domain/investors/investor.repository';
import { Investors } from './../../domain/investors/models/investor';

@Component({
  selector: 'app-investor-page',
  templateUrl: './investor-page.component.html',
  styles: [
    '.investors { min-height: auto; } table { min-width: 1200px; min-height: auto; }mat-icon{ font-size: 29px;} .btn {background-color: #002d40; color: white;}',
  ],
})
export class InvestorPageComponent implements OnInit {
  investorForm!: FormGroup;
  allInvestors: Investors[] = [];
  investormForm!: FormGroup;
  isAppear!: boolean;
  click: boolean = false;
  value: string | undefined;
  submitted: boolean = false;
  submit: boolean = false;
  data!: Investors;
  displayedColumns: string[] = [
    'id',
    'fullName',
    'nickName',
    'nationalId',
    'primaryPhoneNo',
    'secondaryPhoneNo',
    'investorType',
    'startDate',
    'update',
    'delete',
  ];
  constructor(
    private investorsRepository: InvestorsRepository,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.getAllInvestors();
    this.investorsForm();
    this.fetchData();
  }
  investorsForm() {
    this.investorForm = this.formBuilder.group({
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
  fetchData(): void {
    this.investorForm.patchValue(this.data);
  }
  getAllInvestors(): void {
    this.investorsRepository.getList().subscribe((result: any) => {
      this.allInvestors = result;
    });
  }
  onSubmit() {
    if (this.investorForm.valid) {
      this.submitted = true;
      this.investorForm.controls['id'].value
        ? this.UpdateInvestors()
        : this.addInvestors();
      this.investorForm.reset();
    }
  }
  addInvestors() {
    this.investorsRepository.add(this.investorForm.value).subscribe(() => {});
    console.log(this.investorForm.value);
  }

  UpdateInvestors(): void {
    this.investorsRepository
      .update(this.investorForm.value)
      .subscribe(() => {});
  }
  onButtonClick() {
    this.click = !this.click;
  }
  resetTheForm(): void {
    this.investorForm.reset();
  }
}
