package com.mmbeauty.service.service;

import com.mmbeauty.service.model.Sale;
import com.mmbeauty.service.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleServiceImpl implements SaleService{

    private final SaleRepository saleRepository;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public Sale newOrder(Sale sale) {
        return saleRepository.save(sale);
    }

    @Override
    public Sale editOrder(Sale sale) {
        return null;
    }

    @Override
    public List<Sale> getOrderList() {
        return saleRepository.findAll();
    }

    @Override
    public Optional<Sale> getOrder(Long id) {
        return saleRepository.findById(id);
    }
}
