import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { InstallmentRepositry } from 'src/app/domain/installment/installment.repositry';
import { Installment } from 'src/app/domain/installment/models/installment';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-pay-installment',
  templateUrl: './pay-installment.component.html',
  styleUrls: ['./pay-installment.component.scss']
})
export class PayInstallmentComponent implements OnInit {
  installForm!: FormGroup;
  isButtonVisible!: boolean;
  submit: boolean = false;
  paymentAmount!: number;
  paymentDate!: Date;
 constructor(private installmentrepositry: InstallmentRepositry,
  private formBuilder : FormBuilder,
  private snackBar: MatSnackBar,
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
      installmentAmount:[''],
      paymentAmount: ['' , [Validators.required]], 
      paymentDate: [''],
      status:['']
    });
  }

 
  fetchData() {
   if (this.editData)
   {
    this.installForm.controls['id'].setValue(this.editData.id)
    this.installForm.controls['paymentAmount'].setValue(this.editData.paymentAmount)
    this.installForm.controls['installmentAmount'].setValue(this.editData.installmentAmount)
    this.installForm.controls['paymentDate'].setValue(this.editData.paymentDate)
   }
  }

  updateInstallment() {
    this.isButtonVisible = true;
    this.submit = true;
    this.installmentrepositry.update(this.installForm.value).subscribe(() => {
     this.clickPrintButton();
      this.close();
    this.submit = false;
      this.snackBar.open(this.translate.instant('installments.updated-successfuly'),this.translate.instant('installments.close'), {
        duration: 2000,
      })
    },
    () => {
      this.submit=false
    })
  }

  onSubmit()  {
    this.installForm.markAllAsTouched();
    if (this.installForm.valid) 
    { 
      this.installForm.controls['paymentDate'].setValue((new Date()).toISOString().substring(0,10))
      this.paymentAmount = this.installForm.controls['paymentAmount'].value
      this.paymentDate = this.installForm.controls['paymentDate'].value ;
      this.setStatus();
      this.updateInstallment()
      this.installForm.reset(); 
    } 
  }


  close(){
    this.dialog.closeAll()
  }

  setStatus()
  {
    if (this.installForm.controls['installmentAmount'].value=== this.installForm.controls['paymentAmount'].value)
    {
     this.installForm.controls['status'].setValue(1) ;
    }
    else{
      this.installForm.controls['status'].setValue(0) ;
    }
    
  }

 clickPrintButton(){
   const btn = document.getElementById('print')
   btn?.click() 
 } 
}
