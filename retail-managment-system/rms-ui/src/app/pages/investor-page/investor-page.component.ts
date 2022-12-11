import { PopUpInvestorComponent } from './pop-up/pop-up.component';
import { MatDialog } from '@angular/material/dialog';
import { Investors } from './../../domain/investors/models/investor';
import { Component, OnInit } from '@angular/core';
import { InvestorsRepository } from 'src/app/domain/investors/investor.repository';

@Component({
  selector: 'app-investor-page',
  templateUrl: './investor-page.component.html',
})
export class InvestorPageComponent implements OnInit {
  allInvestors: Investors[] = [];
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
