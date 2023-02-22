import { Component, OnInit , ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { InstallmentRepositry } from 'src/app/domain/installment/installment.repositry';
import { Installment } from 'src/app/domain/installment/models/installment';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmDialogComponent } from 'src/app/shared/components/confirm-dialog/confirm-dialog.component';
import { TranslateService } from '@ngx-translate/core';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';



@Component({
  selector: 'app-installment',
  templateUrl: './installment.component.html',
  styleUrls: []
})
export class InstallmentComponent implements OnInit  {
  installmentList:Installment[]=[];
  installForm!: FormGroup;
  submit: boolean = false;
  currentData!: Installment;
  isButtonVisible: boolean = true;
  @ViewChild(MatSort) sort!: MatSort
  dataSource!: MatTableDataSource<Installment>;
  displayedColumns: string[] = [
    'id',
    'installmentAmount',
    'paymentAmount',
    'dueDate',
    'paymentDate',
    'status',
    'actions',
  ];

  constructor(
    private installmentrepositry: InstallmentRepositry,
    private formBuilder: FormBuilder,
    private _snackBar: MatSnackBar,
    private dialog: MatDialog,
    private translate: TranslateService
  ) {}

  ngOnInit(): void {
    this.getAllInstallments();
    this.installmentForm();
  }

  getAllInstallments(): void {
    this.installmentrepositry.getList().subscribe((result) => {
      this.installmentList = result.data;
      this.dataSource = new MatTableDataSource (this.installmentList)
      this.dataSource.sort = this.sort
    });
  }

  installmentForm() {
    this.installForm = this.formBuilder.group({
      id: [''],
      installmentAmount: ['', [Validators.required]],
      paymentAmount: ['', [Validators.required]],
      dueDate: ['', [Validators.required]],
      paymentDate: [''],
      status: [''],
    });
  }

  fetchData(install: Installment): void {
    this.isButtonVisible = false;
    this.installForm.patchValue(install);
    this.currentData = install;
  }

  updateInstallment() {
    this.isButtonVisible = true;
    this.submit = true;
    this.installmentrepositry.update(this.installForm.value).subscribe(
      () => {
        this.getAllInstallments();
        this.submit = false;
        this._snackBar.open(
          this.translate.instant('installments.updated-successfuly'),
          this.translate.instant('installments.close'),
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

  addInstallment() {
    this.isButtonVisible = true;
    this.submit = true;
    this.installmentrepositry.add(this.installForm.value).subscribe(
      () => {
        this.getAllInstallments();
        this.submit = false;
        this._snackBar.open(
          this.translate.instant('installments.added-successfuly'),
          this.translate.instant('installments.close'),
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

  onSubmit() {
    this.installForm.markAllAsTouched();
    if (this.installForm.valid) {
      this.installForm.controls['id'].value
        ? this.updateInstallment()
        : this.addInstallment();
      this.installForm.reset();
    }
  }

  resetForm() {
    this.installForm.controls['id'].value
      ? this.fetchData(this.currentData)
      : this.installForm.reset();
  }

  restartForm(): void {
    this.installForm.reset();
  }

  openConfirmationDialog(install: Installment) {
    let dialogRef = this.dialog.open(ConfirmDialogComponent);
    dialogRef.afterClosed().subscribe((result) => {
      if (result === 'yes') {
        this.deleteInstallment(install);
      }
    });
  }

  deleteInstallment(install: Installment) {
    this.installmentrepositry.delete(install.id).subscribe(() => {
      this.getAllInstallments();
      this._snackBar.open(
        this.translate.instant('installments.delete-successfuly'),
        this.translate.instant('installments.close'),
        {
          duration: 2000,
        }
      );
    });
  }
}
