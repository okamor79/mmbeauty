package com.mmbeauty.service.service;

import com.mmbeauty.service.model.Sale;

import java.util.List;
import java.util.Optional;

public interface SaleService {

    Sale newOrder(Sale sale);

    Sale editOrder(Sale sale);

    List<Sale> getOrderList();

    Optional<Sale> getOrder(Long id);

    List<Sale> getClientOrders(Long id);

}
