package com.sport.sport.service;

import com.sport.sport.entity.Giocatore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GiocatoreService {
    public Giocatore findId(Long id);
    public Long insert(Giocatore e);
    public void delete(Giocatore e);
    public List<Giocatore> findAll();
    public boolean exists(long id);
}
