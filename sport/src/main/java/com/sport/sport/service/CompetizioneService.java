package com.sport.sport.service;

import com.sport.sport.DTO.CompetizioneDTO;
import com.sport.sport.DTO.SquadraDTO;
import com.sport.sport.entity.Competizione;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompetizioneService {
    public Competizione findId(Long id);
    public Long insert(Competizione e);
    public void delete(Competizione e);
    public List<Competizione> findAll();
    public Competizione update(CompetizioneDTO c);
    public boolean isPresent(long id);
    public boolean isValid(CompetizioneDTO s);
}
