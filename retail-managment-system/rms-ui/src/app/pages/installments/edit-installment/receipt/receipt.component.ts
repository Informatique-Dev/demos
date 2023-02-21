import { Component, Input } from '@angular/core';
import { Installment } from 'src/app/domain/installment/models/installment';

@Component({
  selector: 'app-receipt',
  templateUrl: './receipt.component.html',
  styleUrls: ['./receipt.component.scss']
})
export class ReceiptComponent  {
 @Input()install!: Installment;
 @Input() payAmount!: number;
 @Input() payDate!: Date;

 }
