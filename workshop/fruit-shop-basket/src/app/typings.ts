export class BasketItem {
  name: string;
  quantity: number;
}

export class Receipt {
  items: ReceiptItem[];
  discounts: BasketDiscount[]
  total: number;
}

export class ReceiptItem {
  fruit: string;
  quantity: number;
  total: number;
}

export class BasketDiscount {
  name: string;
  amount: number;
}

export class Fruit {
  name: string;
  price: number;
}