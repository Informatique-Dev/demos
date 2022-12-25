import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { InstallmentRepositry } from 'src/app/domain/installment/installment.repositry';
import { Installment } from 'src/app/domain/installment/models/installment';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmDialogComponent } from 'src/app/shared/components/confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-installment',
  templateUrl: './installment.component.html',
  styleUrls: ['./installment.component.scss']
})
export class InstallmentComponent implements OnInit {
  installmentList : Installment[] =[];
  installForm!: FormGroup;
  submit: boolean = false;
  currentData!: Installment;
  isButtonVisible: boolean = true;
  displayedColumns: string[] = [
    'id' ,
    'installmentAmount',
    'paymentAmount',
    'dueDate',
    'paymentDate',
    'status',
    'actions',
  ];


  constructor(
    private installmentrepositry : InstallmentRepositry , 
    private formBuilder : FormBuilder,
    private _snackBar: MatSnackBar,
    private dialog: MatDialog) { }

  ngOnInit(): void {
    this.getAllInstallments();
    this. InstallForm();
  }

  getAllInstallments(): void {
    this.installmentrepositry.getList().subscribe((result) => {
      this.installmentList = result;
    });
  }


  InstallForm() {
    this.installForm = this.formBuilder.group({
      id: [''],
      installmentAmount: ['' , [Validators.required]],
      paymentAmount: ['' , [Validators.required]],
      dueDate: ['', [Validators.required]],
      paymentDate: [''],
      status: [''],
    });
  }

  fetchData(install :Installment): void {
    this.isButtonVisible = false;
    this.installForm.patchValue(install);
    this.currentData = install;
  }

  updateInstallment() {
    this.isButtonVisible = true;
    this.submit = true;
    this.installmentrepositry.update(this.installForm.value).subscribe(() => {
      this.getAllInstallments();
      this.submit = false;
      this._snackBar.open('Installment Updated Successfuly!', 'Close', {
        duration: 2000,
      });
    },
    () => {
      this.submit = false;
    });
  }

 

  addInstallment() {
    this.isButtonVisible = true;
    this.submit = true;
   this.installmentrepositry.add(this.installForm.value).subscribe(() => {
     this.getAllInstallments();
     this.submit = false;
     this._snackBar.open('Installment Added Successfuly!', 'Close', {
      duration: 2000,
    });
  },
  () => {
    this.submit = false;
    });
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

  openConfirmationDialog(install : Installment) {
    let dialogRef = this.dialog.open(ConfirmDialogComponent);
    dialogRef.afterClosed().subscribe((result) => {
      if (result === 'yes') {
        this.DeleteInstallment(install);
      }
    });
  }

  DeleteInstallment(install :Installment) {
    this.installmentrepositry.delete(install.id).subscribe(() => {
      this.getAllInstallments();
      this._snackBar.open('Installment Deleted Successfuly!', 'Close', {
        duration: 2000,
      });
    });
  }


  resetForm() {
    this.installForm.controls['id'].value
      ? this.fetchData(this.currentData)
      : this.installForm.reset();
  }
 

  restartForm(): void {
    this.installForm.reset();
  }

}
