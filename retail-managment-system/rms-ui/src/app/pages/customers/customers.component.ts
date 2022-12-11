import {
  Component,
  OnInit,
  TemplateRef,
  ViewChild,
  ViewEncapsulation,
} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CustomersRepository } from 'src/app/domain/customers/customers.repository';
import { Customers } from 'src/app/domain/customers/models/customers';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.scss'],
  encapsulation: ViewEncapsulation.None,
})
export class CustomersComponent implements OnInit {
  allCustomers: Customers[] = [];
  customersForm!: FormGroup;
  submit: boolean = false;
  currentData!: Customers;
  isButtonVisible: boolean = true;
  @ViewChild('confirmationDialog') confirmationDialog!: TemplateRef<any>;
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
    private build: FormBuilder,
    private _snackBar: MatSnackBar,
    private dialog: MatDialog
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

  fetchData(customer: Customers): void {
    this.isButtonVisible = false;
    this.customersForm.patchValue(customer);
    this.currentData = customer;
  }

  updateCustomer() {
    this.isButtonVisible = true;
    this.submit = true;
    this.customersRepository.update(this.customersForm.value).subscribe(() => {
      this.getAllCustomers();
      this.submit = false;
      this._snackBar.open('Customer Updated Successfuly!', 'Close');
    });
  }

  addCustomer() {
    this.isButtonVisible = true;
    this.submit = true;
    this.customersRepository.add(this.customersForm.value).subscribe(() => {
      this.getAllCustomers();
      this.submit = false;
      this._snackBar.open('Customer Added Successfuly!', 'Close');
    });
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

  restartForm(): void {
    this.customersForm.reset();
  }

  OpenConfirmationDialog(customer: Customers) {
    let dialogRef = this.dialog.open(this.confirmationDialog);
    dialogRef.afterClosed().subscribe((result) => {
      if (result === 'yes') {
        this.customersRepository.delete(customer.id).subscribe(() => {
          this.getAllCustomers();
          this._snackBar.open('Customer Deleted Successfuly!', 'Close');
        });
      }
    });
  }
}
