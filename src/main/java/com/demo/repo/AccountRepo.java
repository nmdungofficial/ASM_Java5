package com.demo.repo;

import com.demo.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepo extends JpaRepository<Account, String> {

    @Query("SELECT a FROM Account a WHERE a.username LIKE %?1% OR a.fullname LIKE %?1% OR a.password LIKE %?1% OR a.email LIKE %?1%")
    Page<Account> findByKeyword(String keyword, Pageable pageable);
}
