import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Fruit } from '../typings';
import { FruitService } from '../fruit.service';

@Component({
  selector: 'app-basket',
  templateUrl: './basket.component.html',
  styleUrls: ['./basket.component.css']
})
export class BasketComponent implements OnInit {
  @Output()
  add = new EventEmitter<string>()
  @Output()
  remove = new EventEmitter<string>()

  fruits: Fruit[] = []

  constructor(private fruitService: FruitService) { }

  ngOnInit() {
    this.fruitService.allFruits().subscribe(fruits => this.fruits = fruits)
  }

  public addFruit(fruit: string) {
      this.add.emit(fruit);
  }

  public removeFruit(fruit: string) {
    this.remove.emit(fruit);
    
  }

}
