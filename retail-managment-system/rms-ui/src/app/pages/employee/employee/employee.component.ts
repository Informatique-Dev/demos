import { PageEvent } from '@angular/material/paginator';
import { MatDialog } from '@angular/material/dialog';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EmployeeRepository } from 'src/app/domain/employee/employee.repository';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TranslateService } from '@ngx-translate/core';
import { ConfirmDialogComponent } from 'src/app/shared/components/confirm-dialog/confirm-dialog.component';
import { Employee } from 'src/app/domain/employee/models/employee';
import { Pagination } from 'src/app/core/models/pagination';
@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.scss']
})
export class EmployeeComponent implements OnInit {
  dataSource: Employee[] = [];
  paginationEmployee!: Pagination
  pageEvent!: PageEvent;
  size: number = 10;
  page: number = 0;
  totaItem: number = 0;
  employeeForm!: FormGroup;
  submit: boolean = false;
  currentData!: Employee;
  isButtonVisible: boolean = true;
  displayedColumns: string[] = [
    'id',
    'fullName',
    'nickName',
    'nationalId',
    'primaryPhoneNo',
    'secondaryPhoneNo',
    'address',
    'job',
    'actions',
  ];
  constructor(
    private employeesRepository: EmployeeRepository,
    private build: FormBuilder,
    private snackBar: MatSnackBar,
    private dialog: MatDialog,
    private translate: TranslateService
  ) { }

  ngOnInit() {
    this.employeeDataForm();
    this.getAllEmployee()

  }
  getAllEmployee() {

    this.employeesRepository.getList({  page: this.page,size: this.size,}).subscribe((result) => {
      this.dataSource = result.data;
      this.totaItem = result.pagination.itemCount;
    });
  }
  onPaginationChange(event: PageEvent) {

    this.page  = event.pageIndex;
    this.size = event.pageSize;
    this.getAllEmployee();
  }
  employeeDataForm() {
    this.employeeForm = this.build.group({
      id: [''],
      fullName: ['', [Validators.required]],
      nickName: [''],
      nationalId: [
        '',
        [
          Validators.required,
          Validators.pattern('^[0-9]{1,14}$'),
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
      job: [''],
    });
  }

  fetchData(employee: Employee): void {
    this.isButtonVisible = false;
    this.employeeForm.patchValue(employee);
    this.currentData = employee;
  }

  updateEmployee() {
    this.isButtonVisible = true;
    this.submit = true;
    this.employeesRepository.update(this.employeeForm.value).subscribe(
      () => {
        this.getAllEmployee();
        this.submit = false;
        this.snackBar.open(
          this.translate.instant('Employee.updated-successfuly'),
          this.translate.instant('Employee.close'),
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

  addEmployee() {
    this.isButtonVisible = true;
    this.submit = true;
    this.employeesRepository.add(this.employeeForm.value).subscribe(
      () => {
        this.getAllEmployee();
        this.submit = false;
        this.snackBar.open(
          this.translate.instant('Employee.added-successfuly'),
          this.translate.instant('Employee.close'),
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
    this.employeeForm.markAllAsTouched();
    if (this.employeeForm.valid) {
      this.employeeForm.controls['id'].value
        ? this.updateEmployee()
        : this.addEmployee();
      this.employeeForm.reset();
    }
  }
  resetForm(): void {
    this.employeeForm.controls['id'].value
      ? this.fetchData(this.currentData)
      : this.employeeForm.reset();
  }

  restartForm(): void {
    this.employeeForm.reset();
  }

  openConfirmationDialog(employee: Employee) {
    let dialogRef = this.dialog.open(ConfirmDialogComponent);
    dialogRef.afterClosed().subscribe((result) => {
      if (result === 'yes') {
        this.deleteEmployee(employee);
      }
    });
  }

  deleteEmployee(employee: Employee) {

    this.employeesRepository.delete(employee.id).subscribe(() => {
      this.getAllEmployee();
      this.snackBar.open(
        this.translate.instant('Employee.delete-successfuly'),
        this.translate.instant('Employee.close'),
        {
          duration: 2000,
        }
      );
    });
  }
}
