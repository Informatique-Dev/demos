import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { InstallmentRepositry } from 'src/app/domain/installment/installment.repositry';
import { Installment } from 'src/app/domain/installment/models/installment';

@Component({
  selector: 'app-installment',
  templateUrl: './installment.component.html',
  styleUrls: ['./installment.component.scss']
})
export class InstallmentComponent implements OnInit {
  InstallmentList : Installment[] =[];
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


  constructor(private installmentrepositry : InstallmentRepositry , private formBuilder : FormBuilder) { }

  ngOnInit(): void {
    this.getAllInstallments();
    this. InstallForm();
  }

  getAllInstallments(): void {
    this.installmentrepositry.getList().subscribe((result) => {
      this.InstallmentList = result;
    });
  }

  DeleteInstallment(install :Installment) {
    this.installmentrepositry.delete(install.id).subscribe(() => {
      this.getAllInstallments();
    });
  }

  InstallForm() {
    this.installForm = this.formBuilder.group({
      id: [''],
      installmentAmount: [''],
      paymentAmount: [''],
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
    });
  }

  addInstallment() {
    this.isButtonVisible = true;
    this.submit = true;
   this.installmentrepositry.add(this.installForm.value).subscribe(() => {
     this.getAllInstallments();
     this.submit = false;
    });
  }

  onSubmit() {
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

}
