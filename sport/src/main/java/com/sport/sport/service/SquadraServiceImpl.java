package com.sport.sport.service;

import com.sport.sport.entity.Squadra;
import com.sport.sport.DAO.SquadraDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SquadraServiceImpl implements SquadraService{
    @Autowired
    private final SquadraDAO squadraRepo;

    public SquadraServiceImpl(SquadraDAO squadraRepo) {
        this.squadraRepo = squadraRepo;
    }

    public List<Squadra> getALLSquadre(){
        List<Squadra> lista = new ArrayList<>();
        squadraRepo.findAll().forEach(
                (squadra) -> lista.add(squadra)
        );
        return lista;
    }

    public Squadra getTopVincite(){
        long id = squadraRepo.getTopVincite();
        return squadraRepo.findById(id).orElse(null);
    }

    public Squadra findId(Long id){
        return squadraRepo.findById(id).orElse(null);
    }

    public Long insert(Squadra e){ return squadraRepo.save(e).getId();}

    public void delete(Squadra e){ squadraRepo.delete(e);}
}
