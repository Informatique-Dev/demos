import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { InstallmentRepositry } from 'src/app/domain/installment/installment.repositry';
import { Installment } from 'src/app/domain/installment/models/installment';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { TranslateService } from '@ngx-translate/core';



@Component({
  selector: 'app-edit-installment',
  templateUrl: './edit-installment.component.html',
  styleUrls: ['./edit-installment.component.scss']
})
export class EditInstallmentComponent implements OnInit {
  installForm!: FormGroup;
  isButtonVisible!: boolean;
  submit: boolean = false;
 
 constructor(private installmentrepositry: InstallmentRepositry,
  private formBuilder : FormBuilder,
  private _snackBar: MatSnackBar,
  private dialog: MatDialog,
  private translate: TranslateService,
  @Inject (MAT_DIALOG_DATA) public editData : Installment
 ) { }

  ngOnInit() {
   this.installmentForm();
   this.fetchData();
  }

  installmentForm() {
    this.installForm = this.formBuilder.group({
      id: [''],
      paymentAmount: ['' , [Validators.required]], 
      paymentDate: [''],
    });
  }

 
  fetchData() {
   if (this.editData)
   {
    this.installForm.controls['paymentAmount'].setValue(this.editData.paymentAmount)
   }
  }

  updateInstallment() {
    this.isButtonVisible = true;
    this.submit = true;
    this.installmentrepositry.update(this.installForm.value).subscribe(() => {
    this.submit = false;
      this._snackBar.open(this.translate.instant('installments.updated-successfuly'),this.translate.instant('installments.close'), {
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
      this.installForm.controls['id'].setValue(this.editData.id) ;
      this.installForm.controls['paymentDate'].setValue((new Date()).toISOString().substring(0,10))
         this.updateInstallment();
         this.installForm.reset();
         this.dialog.closeAll()       
    }
  }

  close(){
    this.dialog.closeAll()
  }

}
