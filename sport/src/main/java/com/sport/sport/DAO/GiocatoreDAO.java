package com.sport.sport.DAO;

import com.sport.sport.entity.Giocatore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GiocatoreDAO extends JpaRepository<Giocatore, Long> {
}
