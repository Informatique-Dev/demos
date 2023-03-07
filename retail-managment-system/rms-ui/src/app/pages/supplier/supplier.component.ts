import { SupplierRepository } from './../../domain/supplier/supplier.repository';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Supplier } from '../../domain/supplier/model/supplier.model';
import { TranslateService } from '@ngx-translate/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ConfirmDialogComponent } from 'src/app/shared/components/confirm-dialog/confirm-dialog.component';
import { log } from 'console';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator, PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-supplier',
  templateUrl: './supplier.component.html',
  styleUrls: ['./supplier.component.scss']
})
export class SupplierComponent implements OnInit {
  suppliersList: Supplier[] = [];
  supplierForm!: FormGroup;
  isButtonVisible: boolean = true;
  submit: boolean = false;
  currentData!: Supplier;
  page: number = 0;
  totaItem: number = 0;
  size: number = 10;
  pagination:any;
  displayedColumns: string[] = [
    'id',
    'version',
    'Name',
    'contactName',
    'primaryPhoneNo',
    'secondaryPhoneNo',
    'address',
    'actions'
  ];

  constructor(private supplierRepository: SupplierRepository,
    private fBuilder: FormBuilder,
    private snackBar: MatSnackBar,
    private dialog: MatDialog,
    private translate: TranslateService) {
      // this.dataSource.paginator = this.paginator;
      this.supplierForm = this.fBuilder.group({
        id: [''],
        version: [''],
        name: ['', [Validators.required]],
        contactName: ['', [Validators.required]],
        primaryPhoneNo: [
          '',
          [Validators.required, Validators.pattern('^01[0-2,5]{1}[0-9]{8}$')],
        ],
        secondaryPhoneNo: [null, [Validators.pattern('^01[0-2,5]{1}[0-9]{8}$')]],
        address: ['', [Validators.required]],
      });
    }

  ngOnInit(): void {
    this.getSuppliers();
  }
  getSuppliers() {
    this.supplierRepository.getList({ page: this.page,size: this.size}).subscribe((response) => {
      this.suppliersList = response.data;
      this.pagination=response.pagination;

    })
  }
  onPaginationChange(event: PageEvent) {
    this.page  = event.pageIndex;
    this.size = event.pageSize;
    this.getSuppliers();
  }
  fetchData(supplier: Supplier): void {
    this.isButtonVisible = false;
    this.supplierForm.patchValue(supplier);
    this.currentData = supplier;
  }

  updateSupplier() {
    this.isButtonVisible = true;
    this.submit = true;
    this.supplierRepository.update(this.supplierForm.value).subscribe(
      () => {
        this.getSuppliers();
        console.log("update");

        this.submit = false;
        this.snackBar.open(
          this.translate.instant('suppliers.updated-successfuly'),
          this.translate.instant('suppliers.close'),
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

  addSupplier() {
    this.isButtonVisible = true;
    this.submit = true;
    this.supplierRepository.add(this.supplierForm.value).subscribe(
      () => {
        this.getSuppliers();
        this.submit = false;
        this.snackBar.open(
          this.translate.instant('supplier.added-successfuly'),
          this.translate.instant('supplier.close'),
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
    this.supplierForm.markAllAsTouched();
    if (this.supplierForm.valid) {
      this.supplierForm.controls['id'].value
        ? this.updateSupplier()
        : this.addSupplier();
      this.supplierForm.reset();
    }
  }
  resetForm(): void {
    this.supplierForm.controls['id'].value
      ? this.fetchData(this.currentData)
      : this.supplierForm.reset();
  }

  restartForm(): void {
    this.supplierForm.reset();
  }

  openConfirmationDialog(supplier: Supplier) {
    let dialogRef = this.dialog.open(ConfirmDialogComponent);
    dialogRef.afterClosed().subscribe((result) => {
      if (result === 'yes') {
        this.deleteSupplier(supplier);
      }
    });
  }

  deleteSupplier(supplier: Supplier) {

    this.supplierRepository.delete(supplier.id).subscribe(() => {
      this.getSuppliers();
      this.snackBar.open(
        this.translate.instant('employee.delete-successfuly'),
        this.translate.instant('employee.close'),
        {
          duration: 2000,
        }
      );
    });
  }
}
