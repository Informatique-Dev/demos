import { Component, OnInit , ViewChild } from '@angular/core';
import { InstallmentRepositry } from 'src/app/domain/installment/installment.repositry';
import { Installment } from 'src/app/domain/installment/models/installment';
import { TranslateService } from '@ngx-translate/core';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { EditInstallmentComponent } from './edit-installment/edit-installment.component';



@Component({
  selector: 'app-installment',
  templateUrl: './installment.component.html',
  styleUrls: ['./installment.component.scss'],
})
export class InstallmentComponent implements OnInit  {
  installmentList:Installment[]=[];
  currentData!: Installment;
  @ViewChild(MatSort) sort!: MatSort 
  dataSource!: MatTableDataSource<Installment>;
  searchText:string='';
  displayedColumns: string[] = [
    'id',
    'customerName',
    'installmentAmount',
    'paymentAmount',
    'dueDate',
    'paymentDate',
    'status',
    'actions',
  ];

  constructor(
    private installmentrepositry: InstallmentRepositry,
    private dialog: MatDialog,
  ) {}
 


  ngOnInit(): void {
    this.getAllInstallments();
   
  }

  getAllInstallments(): void {
    this.installmentrepositry.getList().subscribe((result) => {
      this.installmentList = result;
      this.dataSource = new MatTableDataSource (this.installmentList)
      this.dataSource.sort = this.sort
      this.dataSource.filterPredicate = 
      (data: Installment, filtersJson: string) => {
          const matchFilter:any[] = [];
          const filters = JSON.parse(filtersJson);
          filters.forEach(filter => {
            data[filter.id] = data.order.customer.fullName;
            const val = data[filter.id] === null ? '' : data[filter.id];
            matchFilter.push(val.toLowerCase().includes(filter.value.toLowerCase()));
          });
            return matchFilter.every(Boolean);
        };
    });
  }


 

  openEditDialog(install : Installment) {
     this.dialog.open(EditInstallmentComponent , {
      data:install
     }).afterClosed().subscribe(value =>{
        this.getAllInstallments();
     })
    }

    applyFilter(filterValue: string) {
      const tableFilters :any []= [];
      tableFilters.push({
        id:'',
        value: filterValue
      });
      this.dataSource.filter = JSON.stringify(tableFilters);
    }
  
}
