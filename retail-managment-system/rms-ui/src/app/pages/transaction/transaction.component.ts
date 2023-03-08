import { Component, OnInit } from '@angular/core';
import {TransactionRepository} from 'src/app/domain/transaction/transaction.repository'
import { Transaction, TransactionType } from 'src/app/domain/transaction/models/transaction'
import { Investors } from '../../domain/investors/models/investor'

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.scss']
})
export class TransactionComponent implements OnInit {

  allTransactions : Transaction[] = []
  transactionTypeEnum = Object.values(TransactionType)
  displayedColumns: string[] = ['id','investorName','type','amount','date'];

  constructor(
    private transactionRepository : TransactionRepository,
    ) {}

  ngOnInit(): void {
    this.getAllTransactions()
  }
  
  getAllTransactions():void{
    this.transactionRepository.getList().subscribe((result) =>{
      this.allTransactions = result.data
    })
  }
  
}
