import { Component } from '@angular/core';
import { BasketItem } from './typings';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  basket: Array<BasketItem> = []

  public addItem(name: string) {
    if (this.basket.some(item => item.name === name)) {
      this.basket = this.basket.map(entry => entry.name == name ? { ...entry, quantity: entry.quantity + 1 } : entry);
    }
    else {
      this.basket = [...this.basket, { name, quantity: 1 }]
    }
  }

  public removeItem(name: string) {
    if (this.basket.some(item => item.name === name)) {
      this.basket = this.basket.map(entry => entry.name == name && entry.quantity > 0 ? { ...entry, quantity: entry.quantity - 1 } : entry);
    }
  }

}
