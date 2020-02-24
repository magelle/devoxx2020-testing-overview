import {TestBed} from '@angular/core/testing';
import {Matchers, PactWeb} from '@pact-foundation/pact-web';
import {BasketItem, Receipt} from "./typings";
import {HttpClientModule} from "@angular/common/http";
import { ReceiptService } from './receipt.service';

describe('ReceiptService', () => {
  let provider;

  // Setup Pact mock server for this service
  beforeAll(async () => {

    provider = await new PactWeb({
      consumer: 'frint-shop-basket',
      provider: 'receipt-service',
      port: 1234
    });

    // required for slower CI environments
    await new Promise(resolve => setTimeout(resolve, 1000));

    // Required if run with `singleRun: false`
    await provider.removeInteractions();
  });

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ HttpClientModule ],
      providers: [ ReceiptService ],
    });
  });

   // Verify test
   afterEach(async () => {
    await provider.verify();
  });

  // Create contract
  afterAll(async () => {
    await provider.finalize();
  });

  const basket: BasketItem[] = [
    { fruit: 'Pears', quantity: 12 }
  ];

  const receipt: Receipt = {
    items: [
      {fruit: "Pommes", quantity: 2, total: 200},
      {fruit: "Bananes", quantity: 3, total: 450},
      {fruit: "Cerises", quantity: 1, total: 75}
    ],
    discounts: [
      { name: "5 fruits a day", amount: 100 },
      { name: "Apples lover", amount: 200 },
    ],
    total: 525
  }

  beforeAll((done) => {
    provider.addInteraction({
      state: ``,
      uponReceiving: 'Ask for receipt',
      withRequest: {
        method: 'POST',
        path: '/api/receipt',
        body: basket
      },
      willRespondWith: {
        status: 200,
        body: Matchers.somethingLike(receipt),
        headers: {
          'Content-Type': 'application/json'
        }
      }
    }).then(done, error => done.fail(error));
  });
  
  it('should retreive receipt', (done) => {
    const receiptService: ReceiptService = TestBed.get(ReceiptService);
    receiptService.getReceipt(basket).subscribe(response => {
      expect(response).toEqual(receipt);
      done();
    }, error => {
      done.fail(error);
    });
  });

});
