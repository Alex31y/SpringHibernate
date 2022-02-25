package com.sport.sport.service;

import com.sport.sport.entity.Giocatore;
import com.sport.sport.DAO.GiocatoreDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GiocatoreServiceImpl implements  GiocatoreService{
    @Autowired
    private final GiocatoreDAO giocatoreRepo;

    public GiocatoreServiceImpl(GiocatoreDAO giocatoreRepo) {
        this.giocatoreRepo = giocatoreRepo;
    }

    public Giocatore findId(Long id){
        return giocatoreRepo.findById(id).orElse(null);
    }

    public Long insert(Giocatore e){ return giocatoreRepo.save(e).getId();}

    public void delete(Giocatore e){ giocatoreRepo.delete(e);}

    @Override
    public List<Giocatore> findAll() {
        List<Giocatore> list = new ArrayList<>();
        giocatoreRepo.findAll().forEach(
                (g) -> list.add(g)
        );
        return list;
    }

    @Override
    public boolean exists(long id) {
        if(giocatoreRepo.existsById(id))
            return true;
        else
            return false;
    }
}

