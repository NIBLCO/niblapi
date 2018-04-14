package com.nibl.api.xdcc.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nibl.api.xdcc.domain.Bot;

@Repository
public interface OoinuzaRepository extends JpaRepository<Bot, Integer> {
	
	@Query(value = "SELECT b FROM Bot b JOIN b.packList p WHERE b.statusId = 1")
    Set<Bot> getBots();
    
}
