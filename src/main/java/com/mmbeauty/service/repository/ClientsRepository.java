package com.mmbeauty.service.repository;

import com.mmbeauty.service.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientsRepository extends JpaRepository<Client, Long> {

    @Query("SELECT cl FROM Client cl WHERE cl.email = :client")
    Client getClientByEmail(@Param("client") String email);

}
