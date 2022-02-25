package com.sport.sport.DAO;

import com.sport.sport.entity.Squadra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SquadraDAO extends JpaRepository<Squadra, Long> {
    @Query("select id from Squadra where vittorie = (select max(vittorie) from Squadra)")
    long getTopVincite();

}
