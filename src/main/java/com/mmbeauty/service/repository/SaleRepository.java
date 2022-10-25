package com.mmbeauty.service.repository;

import com.mmbeauty.service.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT o FROM Sale o WHERE o.client = :clientid")
    List<Sale> getOrdersByClient(@Param("clientid") Long id);

}
