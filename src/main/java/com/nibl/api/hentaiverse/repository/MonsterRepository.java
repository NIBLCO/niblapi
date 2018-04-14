package com.nibl.api.hentaiverse.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nibl.api.hentaiverse.domain.Monster;

@Repository
public interface MonsterRepository extends JpaRepository<Monster, Long> {

	// TODO add audit trail for monsters
	@Query(value = "SELECT m FROM Monster m")
    Set<Monster> getMonsters();
	
}
