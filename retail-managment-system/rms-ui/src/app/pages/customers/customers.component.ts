import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CustomersRepository } from 'src/app/domain/customers/customers.repository';
import { customers } from 'src/app/domain/customers/models/customers';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styles: [
    '.main{min-height:800px} .data-table::-webkit-scrollbar{ display: none} .btn {background-color: #002d40; color: white; width: 80px; height:40px} .btnn {background-color: transparent; color: #002d40; width: 50px; height:40px;} .btnn:hover {color:red;} mat-icon { font-size:30px} th,td{line-height: 4; min-width: 140px;}',
  ],
})
export class CustomersComponent implements OnInit {
  allCustomers: customers[] = [];
  customersForm!: FormGroup;
  submit: boolean = false;
  submitted: boolean = false;
  currentData!: customers;
  displayedColumns: string[] = [
    'id',
    'fullName',
    'nickName',
    'customerCode',
    'nationalId',
    'primaryPhoneNo',
    'secondaryPhoneNo',
    'address',
    'trustReceiptNo',
    'actions',
  ];

  constructor(
    private customersRepository: CustomersRepository,
    private build: FormBuilder
  ) {}

  ngOnInit() {
    this.getAllCustomers();
    this.custForm();
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

  getAllCustomers(): void {
    this.customersRepository.getList().subscribe((result) => {
      this.allCustomers = result;
    });
  }

  fetchData(customer: customers): void {
    this.customersForm.patchValue(customer);
    this.currentData = customer;
  }

  updateCustomer() {
    this.submit = true;
    this.customersRepository.update(this.customersForm.value).subscribe(() => {
      this.getAllCustomers();
      this.submit = false;
    });
  }

  addCustomer() {
    this.submit = true;
    this.customersRepository.add(this.customersForm.value).subscribe(() => {
      this.getAllCustomers();
      this.submit = false;
    });
    console.log(this.customersForm.value);
  }

  onSubmit() {
    if (this.customersForm.valid) {
      this.customersForm.controls['id'].value
        ? this.updateCustomer()
        : this.addCustomer();
      this.customersForm.reset();
    }
  }
  resetForm(): void {
    this.customersForm.controls['id'].value
      ? this.fetchData(this.currentData)
      : this.customersForm.reset();
  }

  deleteProduct(customer: customers) {
    this.customersRepository.delete(customer.id).subscribe(() => {
      this.getAllCustomers();
    });
  }
}
