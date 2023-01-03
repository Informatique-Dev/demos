import { Component, OnInit } from '@angular/core';
import {TransactionRepository} from 'src/app/domain/transaction/transaction.repository'
import { Transaction, transactionType } from 'src/app/domain/transaction/models/transaction'
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Investors } from '../../domain/investors/models/investor'
import { InvestorsRepository } from 'src/app/domain/investors/investor.repository';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmDialogComponent } from '../../shared/components/confirm-dialog/confirm-dialog.component';

 

const ELEMENT_DATA : Transaction[] = []

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.scss']
})
export class TransactionComponent implements OnInit {

  transactionPostForm : FormGroup
  allTransactions : Transaction[] = []
  investorNames : Investors[]=[]
  transactionTypeEnum = Object.values(transactionType)
  displayedColumns: string[] = ['id','investorName','type','amount','date','edit-icon' ,'delete-icon'];
  dataSource = ELEMENT_DATA
  currentData!: Transaction
  isButtonVisible: boolean = true
  submit: boolean = false;
  index =0


  constructor(
    private investorsRepository : InvestorsRepository,
    private transactionRepository : TransactionRepository,
    private fb :FormBuilder,
    private SnackBar: MatSnackBar,
    private dialog: MatDialog
    ) {
    this.transactionPostForm = this.fb.group({
      id:[''],
      transactionType: ['',Validators.required],
      amount: ['',[Validators.required,Validators.min(1)]],
      date:['',Validators.required],
      investor:['',Validators.required]
    })
  }

  ngOnInit(): void {
    this.getAllTransactions()
    this.getInvestorData()
  }
  
  getAllTransactions(){
    this.transactionRepository.getList().subscribe((data:any) =>{
      this.allTransactions = data
    })
  }
  
  getInvestorData(){
    this.investorsRepository.getList().subscribe(data => {
      this.investorNames = data      
    });
  }

  addTransaction(){
    this.isButtonVisible = true;
    this.submit = true;
    this.transactionRepository.add(this.transactionPostForm.value).subscribe(
      () => {
        this.getAllTransactions();
        this.submit = false;
        this.SnackBar.open('transaction Added Successfully', 'Close', {
          duration: 2000,
        })
      },()=>{
        this.submit = false;
      }
    )
  }

  fetchData(transaction: Transaction): void {
    this.isButtonVisible = false
    this.transactionPostForm.patchValue(transaction);
    this.currentData = transaction;
  }

  openConfirmationDialog(transaction: Transaction) {
    let dialogRef = this.dialog.open(ConfirmDialogComponent);
    dialogRef.afterClosed().subscribe((result) => {
      if (result === 'yes') {
        this.deleteTransaction(transaction);
      }
    });
  }

  deleteTransaction( transaction:Transaction):void{
    this.transactionRepository.delete(transaction.id).subscribe(() => {
      this.getAllTransactions();
      this.SnackBar.open('Transaction Deleted Successfuly!', 'Close', {
        duration: 2000,
      });
    });
  }

  compareFn(a: Investors, b: Investors) {
    if (!a || !b) return false;
    return a.id === b.id;
  }

  updateTransaction() {
    this.isButtonVisible = true;
    this.submit = true;
    this.transactionRepository.update(this.transactionPostForm.value).subscribe(
      () => {
        this.getAllTransactions();
        this.submit = false;
        this.SnackBar.open('Transaction Updated Successfuly!', 'Close', {
          duration: 2000,
        });
      },
      ()=>{
        this.submit = false;
      }
    );
    console.log(this.transactionPostForm.value)
  }

  onSubmit() {
    this.transactionPostForm.markAllAsTouched();
    if (this.transactionPostForm.valid) {
      this.transactionPostForm.controls['id'].value
        ? this.updateTransaction()
        : this.addTransaction();
      this.transactionPostForm.reset();
    }
  }

  resetForm() {
    this.transactionPostForm.controls['id'].value
      ? this.fetchData(this.currentData)
      : this.transactionPostForm.reset();
  }
  restartForm(){
    this.transactionPostForm.reset();
  }
}
