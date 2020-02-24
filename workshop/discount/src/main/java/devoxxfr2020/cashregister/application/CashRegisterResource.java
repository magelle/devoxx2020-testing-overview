package devoxxfr2020.cashregister.application;


import devoxxfr2020.cashregister.domain.BasketItem;
import devoxxfr2020.cashregister.domain.CashRegister;
import devoxxfr2020.cashregister.domain.Receipt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CashRegisterResource {

    private CashRegister cashRegister;

    public CashRegisterResource(CashRegister cashRegister) {
        this.cashRegister = cashRegister;
    }

    @PostMapping("/api/receipt")
    public Receipt getPrice(@RequestBody List<BasketItem> basketItems) {
        return this.cashRegister.editReceipt(basketItems);
    }

}
