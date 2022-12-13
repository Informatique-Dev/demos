import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { InvestorsRepository } from 'src/app/domain/investors/investor.repository';
import {
  Investors,
  investortypes,
} from './../../domain/investors/models/investor';

@Component({
  selector: 'app-investor-page',
  templateUrl: './investor-page.component.html',
  styles: [
    '.content-size{width:90%}   mat-icon{ font-size: 29px;} .btn {background-color: #002d40; color: white;} .Content-Size{ width: 100%}',
  ],
})
export class InvestorPageComponent implements OnInit {
  investorForm!: FormGroup;
  allInvestors: Investors[] = [];
  click: boolean = false;
  investorsTypes = investortypes;
  value: string | undefined;
  submit: boolean = false;
  data!: Investors;
  currentInvestor!: Investors;
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
      investorType: [''],
      balance: [''],
      startDate: [''],
    });
  }
  fetchData(investor: Investors): void {
    this.investorForm.patchValue(investor);
    this.currentInvestor = investor;
  }
  getAllInvestors(): void {
    this.investorsRepository.getList().subscribe((result: any) => {
      this.allInvestors = result;
    });
  }
  onSubmit() {
    if (this.investorForm.valid) {
      this.investorForm.controls['id'].value
        ? this.UpdateInvestors()
        : this.addInvestors();
      this.investorForm.reset();
    }
  }

  addInvestors() {
    this.investorsRepository.add(this.investorForm.value).subscribe(() => {
      this.getAllInvestors();
      this.submit = false;
    });
  }

  UpdateInvestors(): void {
    this.investorsRepository.update(this.investorForm.value).subscribe(() => {
      this.getAllInvestors();
      this.submit = false;
    });
  }
  resetForm(): void {
    this.investorForm.controls['id'].value
      ? this.fetchData(this.currentInvestor)
      : this.clearTheForm();
  }

  DeleteInvestors(investors: Investors): void {
    this.investorsRepository.delete(investors.id).subscribe(() => {
      this.getAllInvestors();
      this.submit = false;
    });
  }
  clearTheForm(): void {
    this.investorForm.reset();
  }
  restartForm(): void {
    this.clearTheForm();
  }
}
