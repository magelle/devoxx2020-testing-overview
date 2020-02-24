import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from "rxjs/internal/Observable";
import { BasketItem, Receipt } from './typings';

@Injectable({
  providedIn: 'root'
})
export class ReceiptService {
  private receiptUrl  = '/api/receipt';

  constructor(private http: HttpClient) {
  }

  getReceipt(basket: BasketItem[]): Observable<Receipt>{
    return this.http.post<Receipt>(this.receiptUrl, basket)
  }

}
