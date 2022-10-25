package com.mmbeauty.service.service;

import com.mmbeauty.service.model.Sale;

import java.util.List;
import java.util.Optional;

public interface SaleService {

    List<Sale> getOrderList();

    void newOrder(Sale sale);

    List<Sale> getClientOrders(Long id);

    boolean modifyOrder(Sale sale);

}
