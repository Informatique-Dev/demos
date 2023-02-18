import { Component, Inject, OnInit } from '@angular/core';
import {TransactionRepository} from 'src/app/domain/transaction/transaction.repository'
import { Transaction, TransactionType } from 'src/app/domain/transaction/models/transaction'
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { TranslateService } from '@ngx-translate/core';
import { Investors } from 'src/app/domain/investors/models/investor';
import { InvestorsRepository } from 'src/app/domain/investors/investor.repository';




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
  isButtonVisible: boolean = true
  submit: boolean = false;
  allInvestors: Investors[] = [];
  lastId!: Number;
  deposit!: any;
  type : any;
  
  constructor(
    private transactionRepository : TransactionRepository,
    private formBuilder :FormBuilder,
    private snackBar: MatSnackBar,
    private translate : TranslateService,
    private dialog: MatDialog,
    private investorsRepository: InvestorsRepository,
    @Inject(MAT_DIALOG_DATA) public editData: Investors,
  ) { this.deposit=TransactionType.deposit 
        this.type= TransactionType}


  ngOnInit(): void {
    this.getAllTransactions()
    this.TransactionForm()
    this.getAllInvestors() 
  }

  TransactionForm(){
    this.transactionForm = this.formBuilder.group({
      id:[''],
      transactionType: [''],
      amount: ['',[Validators.required,Validators.min(1)]],
      date:[''],
      investor:this.formBuilder.group({
        id :['']
      })
    })
  } 

  getAllTransactions(){
    this.transactionRepository.getList().subscribe((data:any) =>{
      this.allTransactions = data
    })
  }

  getAllInvestors(): void {
    this.investorsRepository.getList().subscribe((result: any) => {
      this.allInvestors = result;
     
      const investorsIds = this.allInvestors.map(ele =>{
        return ele.id
      })
      this.lastId = Math.max(...investorsIds);   
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

  onSubmit() {
    if (this.transactionForm.valid)
     {
      this.transactionForm.controls['date'].setValue((new Date()).toISOString().substring(0,10))
      if (this.editData)
      {
        this.transactionForm.get('investor.id')?.setValue(this.editData.id)
        this.addTransaction();
        this.transactionForm.reset();
        this.dialog.closeAll()
      }else if (!this.editData) {
        this.transactionForm.get('investor.id')?.setValue(this.lastId)
        this.addTransaction();
        this.transactionForm.reset();
        this.dialog.closeAll()
      }
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
