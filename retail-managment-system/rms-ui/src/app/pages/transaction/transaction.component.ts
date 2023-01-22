import { Component, OnInit } from '@angular/core';
import {TransactionRepository} from 'src/app/domain/transaction/transaction.repository'
import { Transaction, TransactionType } from 'src/app/domain/transaction/models/transaction'
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Investors } from '../../domain/investors/models/investor'
import { InvestorsRepository } from 'src/app/domain/investors/investor.repository';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmDialogComponent } from '../../shared/components/confirm-dialog/confirm-dialog.component';
import { TranslateService } from '@ngx-translate/core';


@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.scss']
})
export class TransactionComponent implements OnInit {

  transactionForm!: FormGroup
  allTransactions : Transaction[] = []
  investorNames : Investors[]=[]
  transactionTypeEnum = Object.values(TransactionType)
  displayedColumns: string[] = ['id','investorName','type','amount','date','edit-icon' ,'delete-icon'];
  currentData!: Transaction
  isButtonVisible: boolean = true
  submit: boolean = false;

  constructor(
    private investorsRepository : InvestorsRepository,
    private transactionRepository : TransactionRepository,
    private fb :FormBuilder,
    private snackBar: MatSnackBar,
    private dialog: MatDialog,
    private translate : TranslateService
    ) {
    
  }

  ngOnInit(): void {
    this.getAllTransactions()
    this.getInvestorData()
    this.TransactionForm()
  }
  
  TransactionForm(){
    this.transactionForm = this.fb.group({
      id:[''],
      transactionType: ['',Validators.required],
      amount: ['',[Validators.required,Validators.min(1)]],
      date:['',Validators.required],
      investor:['',Validators.required]
    })
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
    this.transactionRepository.add(this.transactionForm.value).subscribe(
      () => {
        this.getAllTransactions();
        this.submit = false;
        this.snackBar.open(this.translate.instant('transactions.add-message'), this.translate.instant('transactions.close'), {
          duration: 2000,
        })
      },()=>{
        this.submit = false;
      }
    )
  }

  fetchData(transaction: Transaction): void {
    this.isButtonVisible = false
    this.transactionForm.patchValue(transaction);
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
      this.snackBar.open(this.translate.instant('transactions.delete-message'), this.translate.instant('transactions.close'), {
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
    this.transactionRepository.update(this.transactionForm.value).subscribe(
      () => {
        this.getAllTransactions();
        this.submit = false;
        this.snackBar.open(this.translate.instant('transactions.update-message'), this.translate.instant('transactions.close'), {
          duration: 2000,
        });
      },
      ()=>{
        this.submit = false;
      }
    );
  }

  onSubmit() {
    this.transactionForm.markAllAsTouched();
    if (this.transactionForm.valid) {
      this.transactionForm.controls['id'].value
        ? this.updateTransaction()
        : this.addTransaction();
      this.transactionForm.reset();
    }
  }

  resetForm() {
    this.transactionForm.controls['id'].value
      ? this.fetchData(this.currentData)
      : this.transactionForm.reset();
  }
  restartForm(){
    this.transactionForm.reset();
  }
}
