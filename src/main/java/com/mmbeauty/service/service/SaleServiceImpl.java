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
    public List<Sale> getOrderList() {
        return saleRepository.findAll();
    }

    @Override
    public void newOrder(Sale sale) {
        saleRepository.save(sale);
    }

    @Override
    public List<Sale> getClientOrders(Long id) {
        return saleRepository.getOrdersByClient(id);
    }

    @Override
    public boolean modifyOrder(Sale sale) {
        saleRepository.save(sale);
        return true;
    }

    @Override
    public Optional<Sale> getOrder(Long id) {
        return saleRepository.findById(id);
    }

}
