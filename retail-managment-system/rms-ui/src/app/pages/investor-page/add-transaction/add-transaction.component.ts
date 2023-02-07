import { Component, Inject, OnInit } from '@angular/core';
import {TransactionRepository} from 'src/app/domain/transaction/transaction.repository'
import { Transaction, TransactionType } from 'src/app/domain/transaction/models/transaction'
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { TranslateService } from '@ngx-translate/core';
import { Investors } from 'src/app/domain/investors/models/investor';


@Component({
  selector: 'app-add-transaction',
  templateUrl: './add-transaction.component.html',
  styleUrls: ['./add-transaction.component.scss']
})
export class AddTransactionComponent implements OnInit {
  transactionForm!: FormGroup
  allTransactions : Transaction[] = []
  transactionTypeEnum = Object.values(TransactionType)
  displayedColumns: string[] = ['id','investorName','type','amount','date','edit-icon' ,'delete-icon'];
  currentData!: Transaction
  isButtonVisible: boolean = true
  submit: boolean = false;
  
  constructor(
    private transactionRepository : TransactionRepository,
    private formBuilder :FormBuilder,
    private snackBar: MatSnackBar,
    private translate : TranslateService,
    private dialog: MatDialog,
    @Inject(MAT_DIALOG_DATA)
  public editData: Investors,
  ) {}

  ngOnInit(): void {
    this.getAllTransactions()
    this.TransactionForm()
   
  }

  TransactionForm(){
    this.transactionForm = this.formBuilder.group({
      id:[''],
      transactionType: ['',Validators.required],
      amount: ['',[Validators.required,Validators.min(1)]],
      date:['',Validators.required],
      investor:this.formBuilder.group({
        id :[this.editData.id]
      })
    })
  } 

  getAllTransactions(){
    this.transactionRepository.getList().subscribe((data:any) =>{
      this.allTransactions = data
    })
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

  onSubmit() {
    this.transactionForm.markAllAsTouched();
    if (this.transactionForm.valid)
     {
       this.addTransaction();
      this.transactionForm.reset();
      this.dialog.closeAll()   
    }
  }

  resetForm() {
    this.transactionForm.controls['id'].value
      this.transactionForm.reset();
  }

  restartForm(){
    this.transactionForm.reset();
  }

  closeDialog(){
    this.dialog.closeAll();
  }

}
