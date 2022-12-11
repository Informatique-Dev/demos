import { PopUpInvestorComponent } from './pop-up/pop-up.component';
import { MatDialog } from '@angular/material/dialog';
import { Investors } from './../../domain/investors/models/investor';
import { Component, OnInit } from '@angular/core';
import { InvestorsRepository } from 'src/app/domain/investors/investor.repository';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-investor-page',
  templateUrl: './investor-page.component.html',
  styles: ['.btn{background-color:#002d40;color:white;width:50px}'],
})
export class InvestorPageComponent implements OnInit {
  allInvestors: Investors[] = [];
  investormForm!: FormGroup;
  displayedColumns: string[] = [
    'id',
    'fullName',
    'nickName',
    'nationalId',
    'primaryPhoneNo',
    'secondaryPhoneNo',
    'investorType',
    'startDate',
    'update',
    'delete',
  ];
  constructor(
    private investorsRepository: InvestorsRepository,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.getAllInvestors();
  }
  getAllInvestors(): void {
    this.investorsRepository.getList().subscribe((result: any) => {
      this.allInvestors = result;
    });
  }
  openDialog(element: Investors | null) {
    this.dialog.open(PopUpInvestorComponent, {
      data: element,
    });
  }
}
