package com.mmbeauty.service.controller;

import com.mmbeauty.service.model.Sale;
import com.mmbeauty.service.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class SaleController {

    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping("/list")
    public List<Sale> getOrderList() {
        return saleService.getOrderList();
    }
/*
    @GetMapping("/list/{id}")
    public Optional<Sale> getClientOrders(@PathVariable("id") Long id) {
        return saleService.getClientOrders(id);
    }
*/
}
