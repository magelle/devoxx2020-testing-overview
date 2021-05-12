package devoxxfr2020.cashregister.controller;

import devoxxfr2020.cashregister.model.BasketItem;
import devoxxfr2020.cashregister.service.CashRegisterService;
import devoxxfr2020.cashregister.model.Receipt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CashRegisterResource {

    private final CashRegisterService cashRegisterService;

    public CashRegisterResource(CashRegisterService cashRegisterService) {
        this.cashRegisterService = cashRegisterService;
    }

    @PostMapping("/api/receipt")
    public Receipt getPrice(@RequestBody List<BasketItem> basketItems) {
        return this.cashRegisterService.editReceipt(basketItems);
    }

}
