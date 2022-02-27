package com.sport.sport.service;

import com.sport.sport.DTO.SquadraDTO;
import com.sport.sport.entity.Squadra;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SquadraService {

    public List<Squadra> getALLSquadre();
    public Squadra getTopVincite();
    public Squadra findId(Long id);
    public Long insert(Squadra e);
    public Squadra update(SquadraDTO s);
    public void delete(Squadra e);
    public boolean isPresent(long id);
    public boolean isValid(SquadraDTO s);
}
