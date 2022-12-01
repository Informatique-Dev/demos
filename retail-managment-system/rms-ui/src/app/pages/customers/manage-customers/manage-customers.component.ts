import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { CustomersRepository } from 'src/app/domain/customers/customers.repository';
import { customers } from 'src/app/domain/customers/models/customers';

@Component({
  selector: 'app-manage-customers',
  templateUrl: './manage-customers.component.html',
  styles: ['.manage-customer { min-width: 250px; min-height: 520px; }'],
})
export class ManageCustomersComponent implements OnInit {
  customersForm!: FormGroup;
  allCustomers: customers[] = [];
  submit: boolean = false;
  submitted: boolean = false;

  constructor(
    private customersRepository: CustomersRepository,
    private build: FormBuilder,
    public dialogRef: MatDialogRef<ManageCustomersComponent>,
    @Inject(MAT_DIALOG_DATA) public data: customers
  ) {}

  ngOnInit() {
    this.custForm();
    this.fetchData();
  }

  custForm() {
    this.customersForm = this.build.group({
      id: [''],
      fullName: ['', [Validators.required]],
      nickName: [''],
      customerCode: [''],
      version: [''],
      nationalId: [
        '',
        [
          Validators.required,
          Validators.pattern('^[0-9]*$'),
          Validators.minLength(14),
          Validators.maxLength(14),
        ],
      ],
      primaryPhoneNo: [
        '',
        [Validators.required, Validators.pattern('^01[0-2,5]{1}[0-9]{8}$')],
      ],
      secondaryPhoneNo: ['', [Validators.pattern('^01[0-2,5]{1}[0-9]{8}$')]],
      address: ['', [Validators.required]],
      trustReceiptNo: [''],
    });
  }

  fetchData(): void {
    this.customersForm.patchValue(this.data);
  }

  updateCustomer() {
    this.submit = true;
    this.customersRepository.update(this.customersForm.value).subscribe(() => {
      this.submit = false;
      this.dialogRef.close();
    });
  }

  addCustomer() {
    this.submit = true;
    this.customersRepository.add(this.customersForm.value).subscribe(() => {
      this.submit = false;
    });
    console.log(this.customersForm.value);
  }

  onSubmit() {
    if (this.customersForm.valid) {
      this.submitted = true;
      this.customersForm.controls['id'].value
        ? this.updateCustomer()
        : this.addCustomer();
      this.customersForm.reset();
    }
  }

  resetForm(): void {
    this.customersForm.reset();
  }
}
