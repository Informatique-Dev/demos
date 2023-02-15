import { Component, Inject, Input, OnInit } from '@angular/core';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Installment } from 'src/app/domain/installment/models/installment';

@Component({
  selector: 'app-receipt',
  templateUrl: './receipt.component.html',
  styleUrls: ['./receipt.component.scss']
})
export class ReceiptComponent implements OnInit {
  print: boolean =false
 @Input()install!: Installment;
 @Input() payAmount!: number;
 @Input() payDate!: Date;

  constructor(@Inject (MAT_DIALOG_DATA) public editData : Installment,  private dialog: MatDialog,) { }

  ngOnInit(): void {

  }

 

}
