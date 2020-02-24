import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BasketComponent } from './basket/basket.component';
import { ReceiptService } from './receipt.service';
import { HttpClientModule } from '@angular/common/http';
import { ReceiptComponent } from './receipt/receipt.component';

import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  declarations: [
    AppComponent,
    BasketComponent,
    ReceiptComponent
  ],
  imports: [
    NgbModule,
    BrowserModule,
    HttpClientModule
  ],
  providers: [ReceiptService],
  bootstrap: [AppComponent]
})
export class AppModule { }
