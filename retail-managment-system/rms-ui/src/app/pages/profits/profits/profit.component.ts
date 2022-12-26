import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Profit } from 'src/app/domain/profit/models/profit';
import { RepositoryService } from 'src/app/domain/profit/repository.repository';
import { ConfirmDialogComponent } from 'src/app/shared/components/confirm-dialog/confirm-dialog.component';
@Component({
  selector: 'app-profit',
  templateUrl: './profit.component.html',
  styleUrls: ['./profit.component.scss'],
})
export class ProfitComponent implements OnInit {
  allProfits:Profit[] = [];
  addProfitsForm!:FormGroup;
  submit: boolean = false;
  currentData!:Profit;
  isButtonVisible: boolean = true;
  displayedColumns: string[] = ['id', 'bookNo', 'profitAmount','date','calculated','actions'];
constructor( private profitsRepo:RepositoryService,private buildForm:FormBuilder ,private dialog: MatDialog,
  private SnackBar: MatSnackBar) { }
  ngOnInit(): void {
    this.getAllProfits();
    this.profitsForm();
  }
  profitsForm() {
    this.addProfitsForm = this.buildForm.group({
      id: [''],
      bookNo: ['',[Validators.required]],
      profitAmount: ['',[Validators.required]],
      date: ['',[Validators.required]],
      calculated: ['',[Validators.required] ],
    });
  }
  getAllProfits():void{
    this.profitsRepo.getList().subscribe((results)=>{
       this.allProfits=results;
    })
  }
  editData(profit: Profit): void {
     this.isButtonVisible = false;
    this.addProfitsForm.patchValue(profit);
    this.currentData = profit;
  }
  updateProfit(){
   this.isButtonVisible=true;
    this.submit = true;
    this.profitsRepo.update(this.addProfitsForm.value).subscribe(() => {
      this.getAllProfits()
      this.submit = false;
        this.SnackBar.open('Profit Updated Successfully', 'Close', {
          duration: 2000,
        });
      },
      () => {
        this.submit = false});
  }
  addProfit() {
   this.isButtonVisible = true;
    this.submit = true;
     this.profitsRepo.add(this.addProfitsForm.value).subscribe(() => {
    this.getAllProfits();
    this.submit = false;
      this.SnackBar.open('Profit Added Successfully', 'Close', {
        duration: 2000,
      });
    },
    () => {
      this.submit = false; }
   );}
  onSubmit() {
    this.addProfitsForm.markAllAsTouched();
    if (this.addProfitsForm.valid ) {
      this.addProfitsForm.controls['id'].value
        ? this.updateProfit()
        : this.addProfit();
     this.addProfitsForm.reset();
    }
  }

  resetForm(): void {
    this.addProfitsForm.controls['id'].value
      ? this.editData(this.currentData)
      : this.addProfitsForm.reset();
  }
  restartForm(): void {
    this.addProfitsForm.reset();
  }
  openConfirmationDialog(Profits: Profit) {
    let dialogRef = this.dialog.open(ConfirmDialogComponent);
    dialogRef.afterClosed().subscribe((result) => {
      if (result === 'yes') {
        this.deleteProfit(Profits);
      }
    });
  }

  deleteProfit(profit: Profit) {
    this.profitsRepo.delete(profit.id).subscribe(() => {
      this.getAllProfits();
      this.SnackBar.open('Profits Deleted Successfully', 'Close', {
        duration: 2000,
      });
    });
  }
}
