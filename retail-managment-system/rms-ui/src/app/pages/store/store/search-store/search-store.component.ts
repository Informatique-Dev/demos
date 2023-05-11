import { Component, OnInit } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TranslateService } from '@ngx-translate/core';
import { EmployeeRepository } from 'src/app/domain/employee/employee.repository';
import { Employee } from 'src/app/domain/employee/models/employee';
import { Store } from 'src/app/domain/store/models/store';
import { storeRepository } from 'src/app/domain/store/store.repository';

@Component({
  selector: 'app-search-store',
  templateUrl: './search-store.component.html',
  styleUrls: ['./search-store.component.scss']
})
export class SearchStoreComponent implements OnInit {

  employees:Employee[]=[]
  allStores: Store[] = []
  isButtonVisible: boolean = true
  currentResponsible:string = ''
  totaItem: number = 0
  size: number = 10
  page: number = 0
  displayedColumns: string[] = [
    'id',
    'name',
    'responsible',
    'address'
  ]
  constructor(
    private employeeRepository: EmployeeRepository,
    private storeRepository: storeRepository,
    private snackBar: MatSnackBar,
    private translate: TranslateService,
  ) { }

  ngOnInit(): void {
    this.getAllEmployee()
  }



  getAllEmployee(): void {
    this.employeeRepository.getList().subscribe((result) => {
      this.employees = result.data;
    });
  }

  getStores(responsible:string){
    this.storeRepository.filterStoreByEmployee(this.page,this.size,responsible).subscribe(result=>{
      this.allStores = result.data
      this.currentResponsible = responsible
      this.totaItem = result.pagination.itemCount
      if(this.allStores.length == 0){
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

   onPaginationChange(event: PageEvent) {

    this.page  = event.pageIndex;
    this.size = event.pageSize;
    this.getStores(this.currentResponsible)
  }
}
