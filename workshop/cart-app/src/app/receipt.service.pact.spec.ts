import {TestBed} from '@angular/core/testing';
import {Matchers, PactWeb} from '@pact-foundation/pact-web';
import {BasketItem, Receipt} from "./typings";
import {HttpClientModule} from "@angular/common/http";
import { ReceiptService } from './receipt.service';

describe('ReceiptService', () => {
  let provider;

  beforeAll(function (done) {
    provider = new PactWeb({
      consumer: 'Fruit shop Basket',
      provider: 'Receipt Service',
      port: 1234,
      host: '127.0.0.1',
    });

    // required for slower CI environments
    setTimeout(done, 2000);

    // Required if run with `singleRun: false`
    provider.removeInteractions();
  });

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientModule
      ],
      providers: [
        ReceiptService
      ],
    });
  });

  afterEach((done) => {
    provider.verify().then(done, e => done.fail(e));
  });


  afterAll(function (done) {
    provider.finalize()
      .then(function () {
        done();
      }, function (err) {
        done.fail(err);
      });
  });


  describe('get Product', () => {
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
          path: '/receipt',
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

    it('should get Product informations', (done) => {
      const receiptService: ReceiptService = TestBed.get(ReceiptService);
      receiptService.getReceipt(basket).subscribe(response => {
        expect(response).toEqual(receipt);
        done();
      }, error => {
        done.fail(error);
      });
    });

  });

});
