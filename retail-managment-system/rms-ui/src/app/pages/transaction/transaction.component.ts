import { Component, OnInit } from '@angular/core';
import { ResourceService } from 'src/app/core/services/resource.service';
import {TransactionRepository} from 'src/app/domain/transaction/transaction.repository'
import { Transaction, transactionType } from 'src/app/domain/transaction/models/transaction'
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

const ELEMENT_DATA : Transaction[] = []
// export interface PeriodicElement {
//   name: string;
//   position: number;
//   weight: number;
//   symbol: string;
// }
// const ELEMENT_DATA: PeriodicElement[] = [
//   {position: 1, name: 'Hydrogen', weight: 1.0079, symbol: 'H'},
//   {position: 2, name: 'Helium', weight: 4.0026, symbol: 'He'},
//   {position: 3, name: 'Lithium', weight: 6.941, symbol: 'Li'},
//   {position: 4, name: 'Beryllium', weight: 9.0122, symbol: 'Be'},
//   {position: 5, name: 'Boron', weight: 10.811, symbol: 'B'},
//   {position: 6, name: 'Carbon', weight: 12.0107, symbol: 'C'},
//   {position: 7, name: 'Nitrogen', weight: 14.0067, symbol: 'N'},
//   {position: 8, name: 'Oxygen', weight: 15.9994, symbol: 'O'},
//   {position: 9, name: 'Fluorine', weight: 18.9984, symbol: 'F'},
//   {position: 10, name: 'Neon', weight: 20.1797, symbol: 'Ne'},
// ];


@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.scss']
})
export class TransactionComponent implements OnInit {

  transactionPostForm : FormGroup
  allTransactions : Transaction[] = []
  transactionTypeEnum = Object.values(transactionType)
  displayedColumns: string[] = ['id', 'version', 'type','amount','date','edit-icon' ,'delete-icon'];
  dataSource = ELEMENT_DATA;

  constructor(
    private transactionRepository : TransactionRepository,
    private fb :FormBuilder
    ) {
    this.transactionPostForm = this.fb.group({
      transactionVersion: ['',Validators.required],
      transactionType: ['',Validators.required],
      transactionAmount: ['',Validators.required],
      transactionDate:['',Validators.required]
    })
  }

  ngOnInit(): void {
    this.getAllTransactions()
  }
  
  getAllTransactions(){
    this.transactionRepository.getList().subscribe(data =>{
      this.allTransactions = data
    })
  }
  
  deleteProduct(transaction: Transaction) {
    this.transactionRepository.delete(transaction.id).subscribe(() => {
      this.getAllTransactions();
    });
  }
}
