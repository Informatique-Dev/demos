
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Profit } from 'src/app/domain/profit/models/profit';
import { RepositoryService } from 'src/app/domain/profit/repository.repository';

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

constructor( private profitsRepo:RepositoryService,private buildForm:FormBuilder ) {

   }

  ngOnInit(): void {
    this.getAllProfits();
    this.profitsForm();
  }
  profitsForm() {
    this.addProfitsForm = this.buildForm.group({
      id: [''],
      bookNo: [''],
      profitAmount: ['',],
      date: [''],
      calculated: ['', ],
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
    });
  }
  addProfit() {
   this.isButtonVisible = true;
    this.submit = true;
     this.profitsRepo.add(this.addProfitsForm.value).subscribe(() => {
    this.getAllProfits();
     this.submit = false;
    });
  }

  onSubmit() {
    if (this.addProfitsForm.valid) {

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

  deleteProfit(profit: Profit) {
    this.profitsRepo.delete(profit.id).subscribe(() => {
      this.getAllProfits();
    });
  }
}
