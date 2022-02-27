package com.sport.sport.service;

import com.sport.sport.DTO.AllenatoreDTO;
import com.sport.sport.DTO.SquadraDTO;
import com.sport.sport.entity.Allenatore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AllenatoreService {
    public Allenatore findId(Long id);
    public Long insert(Allenatore e);
    public void delete(Allenatore e);
    public List<Allenatore> findAll();
    public Allenatore update(AllenatoreDTO a);
    public boolean isPresent(long id);
    public boolean isValid(AllenatoreDTO s);
}
