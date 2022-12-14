import { ConfirmDialogComponent } from './../../shared/components/confirm-dialog/confirm-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
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
    '.content-size{width:90%}   mat-icon{ font-size: 29px;} .btn {background-color: #002d40; color: white;}.fix-table{height:600px}',
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
    private formBuilder: FormBuilder,
    private SnackBar: MatSnackBar,
    private dialog: MatDialog
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
    this.investorForm.markAllAsTouched();
    this.submit = true;
    if (this.investorForm.valid) {
      this.investorForm.controls['id'].value
        ? this.UpdateInvestors()
        : this.addInvestors();
      this.investorForm.reset();
    } else if (this.investorForm.invalid) {
      return;
    }
  }

  addInvestors() {
    this.submit = true;
    this.investorsRepository.add(this.investorForm.value).subscribe(
      () => {
        this.getAllInvestors();
        this.submit = false;
        this.SnackBar.open('Investor Added Successfully', 'Close', {
          duration: 2000,
        });
      },
      () => {
        this.submit = false;
      }
    );
  }

  UpdateInvestors(): void {
    this.submit = true;
    this.investorsRepository.update(this.investorForm.value).subscribe(
      () => {
        this.getAllInvestors();
        this.submit = false;
        this.SnackBar.open('Investor Updated Successfully', 'Close', {
          duration: 2000,
        });
      },
      () => {
        this.submit = false;
      }
    );
  }
  resetForm(): void {
    this.investorForm.controls['id'].value
      ? this.fetchData(this.currentInvestor)
      : this.clearTheForm();
  }
  openConfirmationDialog(investors: Investors) {
    let dialogRef = this.dialog.open(ConfirmDialogComponent);
    dialogRef.afterClosed().subscribe((result) => {
      if (result === 'yes') {
        this.DeleteInvestors(investors);
      }
    });
  }

  DeleteInvestors(investors: Investors): void {
    this.investorsRepository.delete(investors.id).subscribe(() => {
      this.getAllInvestors();
      this.submit = false;
      this.SnackBar.open('Investor Deleted Successfully', 'Close', {
        duration: 2000,
      });
    });
  }
  clearTheForm(): void {
    this.investorForm.reset();
  }
  restartForm(): void {
    this.clearTheForm();
  }
}
