package com.demo.repo;

import com.demo.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartDetailRepo extends JpaRepository<OrderDetail, Long> {
    @Query("SELECT c FROM OrderDetail c WHERE c.order.id=?1")
    List<OrderDetail> findByCartid(Long id);
}
