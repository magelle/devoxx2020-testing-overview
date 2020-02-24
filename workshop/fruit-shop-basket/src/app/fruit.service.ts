import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from "rxjs/internal/Observable";
import { Fruit } from './typings';

@Injectable({
  providedIn: 'root'
})
export class FruitService {
  private fruitsUrl = '/api/fruits';

  constructor(private http: HttpClient) {
  }

  allFruits(): Observable<Fruit[]> {
    return this.http.get<Fruit[]>(this.fruitsUrl)
  }

}
