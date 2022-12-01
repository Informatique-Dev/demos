import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { CustomersRepository } from 'src/app/domain/customers/customers.repository';
import { customers } from 'src/app/domain/customers/models/customers';
import { ManageCustomersComponent } from './manage-customers/manage-customers.component';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styles: [
    '.customers { min-height: auto; } table { min-width: 1200px; min-height: auto; }',
  ],
})
export class CustomersComponent implements OnInit {
  allCustomers: customers[] = [];
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
    'update',
    'delete',
  ];

  constructor(
    private customersRepository: CustomersRepository,
    public dialog: MatDialog
  ) {}

  ngOnInit() {
    this.getAllCustomers();
  }
  getAllCustomers(): void {
    this.customersRepository.getList().subscribe((result) => {
      this.allCustomers = result;
    });
  }
  openDialog(element: customers | null) {
    this.dialog
      .open(ManageCustomersComponent, {
        data: element,
      })
      .afterClosed()
      .subscribe(() => {
        this.getAllCustomers();
      });
  }
}
