import { Component, Input, OnChanges } from '@angular/core';
import { Receipt, BasketItem } from '../typings';
import { ReceiptService } from '../receipt.service';

@Component({
  selector: 'app-receipt',
  templateUrl: './receipt.component.html',
  styleUrls: ['./receipt.component.css']
})
export class ReceiptComponent implements OnChanges {

  @Input() basket: BasketItem[]
  receipt: Receipt

  constructor(private receiptService: ReceiptService) { }

  ngOnChanges() {
    this.receiptService.getReceipt(this.basket)
      .subscribe(receipt => this.receipt = receipt)
  }


}
