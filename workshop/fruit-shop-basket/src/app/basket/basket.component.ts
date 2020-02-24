import { Component, OnInit } from '@angular/core';
import { ReceiptService } from '../receipt.service';
import { Receipt } from '../typings';

@Component({
  selector: 'app-basket',
  templateUrl: './basket.component.html',
  styleUrls: ['./basket.component.css']
})
export class BasketComponent implements OnInit {
  receipt: Receipt

  constructor(private receiptService: ReceiptService) { }

  ngOnInit() {
    this.receiptService.getReceipt([
      { name: 'Pommes', quantity: 12 },
      { name: 'Bananes', quantity: 2 },
      { name: 'Cerises', quantity: 5 }
    ]).subscribe(receipt => this.receipt = receipt)
  }

}
