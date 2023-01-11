import { ConfirmDialogComponent } from './../../shared/components/confirm-dialog/confirm-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { InvestorsRepository } from 'src/app/domain/investors/investor.repository';

import {
  Investors,
  InvestorTypes,
} from './../../domain/investors/models/investor';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-investor-page',
  templateUrl: './investor-page.component.html',
  styleUrls: ['./investor-page.component.scss'],
})
export class InvestorPageComponent implements OnInit {
  investorForm!: FormGroup;
  allInvestors: Investors[] = [];
  click: boolean = false;
  investorsTypes = InvestorTypes;
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
    private _snackBar: MatSnackBar,
    private dialog: MatDialog,
    private translate: TranslateService
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
        ? this.updateInvestors()
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
        this._snackBar.open(
          this.translate.instant('investors.added-successfuly'),
          this.translate.instant('investors.close'),
          {
            duration: 2000,
          }
        );
      },
      () => {
        this.submit = false;
      }
    );
  }

  updateInvestors(): void {
    this.submit = true;
    this.investorsRepository.update(this.investorForm.value).subscribe(
      () => {
        this.getAllInvestors();
        this.submit = false;
        this._snackBar.open(
          this.translate.instant('investors.updated-successfuly'),
          this.translate.instant('investors.close'),
          {
            duration: 2000,
          }
        );
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
        this.deleteInvestors(investors);
      }
    });
  }

  deleteInvestors(investors: Investors): void {
    this.investorsRepository.delete(investors.id).subscribe(() => {
      this.getAllInvestors();
      this._snackBar.open(
        this.translate.instant('investors.delete-successfuly'),
        this.translate.instant('investors.close'),
        {
          duration: 2000,
        }
      );
    });
  }
  clearTheForm(): void {
    this.investorForm.reset();
  }
  restartForm(): void {
    this.clearTheForm();
  }
}
