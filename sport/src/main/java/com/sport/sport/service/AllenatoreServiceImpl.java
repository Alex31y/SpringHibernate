package com.sport.sport.service;

import com.sport.sport.entity.Allenatore;
import com.sport.sport.DAO.AllenatoreDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AllenatoreServiceImpl implements AllenatoreService{
    @Autowired
    private final AllenatoreDAO allenatoreRepository;

    public AllenatoreServiceImpl(AllenatoreDAO allenatoreRepository) {
        this.allenatoreRepository = allenatoreRepository;
    }

    public Allenatore findId(Long id){
        return allenatoreRepository.findById(id).orElse(null);
    }

    public Long insert(Allenatore e){ return allenatoreRepository.save(e).getId();}

    public void delete(Allenatore e){ allenatoreRepository.delete(e);}

    @Override
    public List<Allenatore> findAll() {
        List<Allenatore> list = new ArrayList<>();
        allenatoreRepository.findAll().forEach(
                (a) -> list.add(a)
        );
        return list;
    }
}
