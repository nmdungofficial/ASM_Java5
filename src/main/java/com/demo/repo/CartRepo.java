package com.demo.repo;

import com.demo.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepo extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o WHERE o.address =?1 AND o.account.username=?2")
    List<Order> findByAddress(String address,String username);

    @Query("SELECT o FROM Order o WHERE o.account.username=?1")
    List<Order> findByUsername(String username);
}
