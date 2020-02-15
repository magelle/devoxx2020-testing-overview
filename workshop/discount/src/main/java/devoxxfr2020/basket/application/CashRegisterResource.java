package devoxxfr2020.basket.application;


import devoxxfr2020.basket.domain.BasketItem;
import devoxxfr2020.basket.domain.Receipt;
import devoxxfr2020.basket.domain.CashRegister;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/basket")
public class CashRegisterResource {

    private CashRegister cashRegister;

    public CashRegisterResource(CashRegister cashRegister) {
        this.cashRegister = cashRegister;
    }

    @PostMapping
    public Receipt getPrice(@RequestBody List<BasketItem> basketItems) {
        return this.cashRegister.editReceipt(basketItems);
    }

}
