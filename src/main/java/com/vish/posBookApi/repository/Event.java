package com.vish.posBookApi.repository;

import com.vish.posBookApi.model.Events;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Event extends JpaRepository<Events,Long> {
    List<Events> findByAccountAndSecurity(String account, String security);
}
