package com.mmbeauty.service.controller;

import com.mmbeauty.service.model.Sale;
import com.mmbeauty.service.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/create")
    public ResponseEntity<Void> createOrder(@RequestBody Sale sale) {
        saleService.newOrder(sale);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
