package com.mmbeauty.service.controller;

import com.mmbeauty.service.model.Sale;
import com.mmbeauty.service.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}
