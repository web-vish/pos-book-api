package com.vish.posBookApi.repository;

import com.vish.posBookApi.model.Events;
import com.vish.posBookApi.model.Positions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Position extends JpaRepository<Positions, Long> {
    Positions findByAccountAndSecurity(String account, String security);
}
