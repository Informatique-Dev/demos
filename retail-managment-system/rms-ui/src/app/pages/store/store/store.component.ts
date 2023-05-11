import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { PageEvent } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Store } from 'src/app/domain/store/models/store';
import { storeRepository } from 'src/app/domain/store/store.repository';
import { TranslateService } from '@ngx-translate/core';
import { Employee } from 'src/app/domain/employee/models/employee';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmDialogComponent } from 'src/app/shared/components/confirm-dialog/confirm-dialog.component';
import { EmployeeRepository } from 'src/app/domain/employee/employee.repository';

@Component({
  selector: 'app-store',
  templateUrl: './store.component.html',
  styleUrls: ['./store.component.scss']
})
export class StoreComponent implements OnInit {

  allStores:Store[]=[]
  size: number = 10
  page: number = 0
  totaItem: number = 0
  currentData!: Store
  currentResponsible:string = ''
  isButtonVisible: boolean = true
  isTableVisible:boolean = false
  submit: boolean = false
  employees:Employee[]=[]
  storeForm!:FormGroup
  displayedColumns: string[] = [
    'id',
    'name',
    'address',
    'responsible',
    'update',
    'delete'
  ]
  constructor(
    private storeRepository: storeRepository,
    private build : FormBuilder,
    private snackBar: MatSnackBar,
    private translate: TranslateService,
    private dialog: MatDialog,
    private employeeRepository: EmployeeRepository
    ) { }

  ngOnInit(): void {
    this.storgeForm()
    this.getAllEmployee()
  }

 getStores(responsible:string){
  this.storeRepository.filterStoreByEmployee(this.page,this.size,responsible).subscribe(result=>{
    this.allStores = result.data
    this.isTableVisible = true
    this.currentResponsible = responsible
    this.totaItem = result.pagination.itemCount
    if(this.allStores.length == 0){
      this.isTableVisible = false
      this.snackBar.open(
        this.translate.instant('store.no-stores'),
        this.translate.instant('store.close'),
        {
          duration: 2000,
        }
      ); 
    }
  })
 }

  storgeForm(){
    this.storeForm = this.build.group({
      id:[''],
      name:['',Validators.maxLength(100)],
      address:['',[Validators.maxLength(100),Validators.required]],
      responsible:['']
    })
  }

  onPaginationChange(event: PageEvent) {

    this.page  = event.pageIndex;
    this.size = event.pageSize;
    this.getStores(this.currentResponsible)
  }
  
  fetchData(store:Store): void {
    this.isButtonVisible = false;
    this.storeForm.patchValue(store)
    this.currentData = store
  }

  updateStore() {
    this.isButtonVisible = true;
    this.submit = true;
    this.storeRepository.update(this.storeForm.value).subscribe(
      () => {
        this.getStores(this.currentResponsible)
        this.submit = false;
        this.snackBar.open(
          this.translate.instant('store.updated-successfuly'),
          this.translate.instant('store.close'),
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

  addStore() {
    this.isButtonVisible = true;
    this.submit = true;
    this.storeRepository.add(this.storeForm.value).subscribe(
      () => {
        this.getStores(this.currentResponsible)
        this.submit = false;
        this.snackBar.open(
          this.translate.instant('store.added-successfuly'),
          this.translate.instant('store.close'),
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

  deleteStore(store: Store) {
    this.storeRepository.delete(store.id).subscribe(() => {
      this.getStores(this.currentResponsible)
      this.snackBar.open(
        this.translate.instant('store.delete-successfuly'),
        this.translate.instant('store.close'),
        {
          duration: 2000,
        }
      );
    });
  }

  onSubmit() {
    this.storeForm.markAllAsTouched();
    if (this.storeForm.valid) {
      this.storeForm.controls['id'].value
        ? this.updateStore()
        : this.addStore();
      this.storeForm.reset();
    }
  }

  resetForm() {
    this.storeForm.controls['id'].value
      ? this.fetchData(this.currentData)
      : this.storeForm.reset();
  }
  compareFn(a: Employee, b: Employee) {
    if (!a || !b) return false;
    return a.id === b.id;
  }

  restartForm(): void {
    this.storeForm.reset();
  }

  openConfirmationDialog(store: Store) {
    let dialogRef = this.dialog.open(ConfirmDialogComponent);
    dialogRef.afterClosed().subscribe((result) => {
      if (result === 'yes') {
        this.deleteStore(store);
      }
    });
  }

  getAllEmployee(): void {
    this.employeeRepository.getList().subscribe((result) => {
      this.employees = result.data;
    });
  }

}
