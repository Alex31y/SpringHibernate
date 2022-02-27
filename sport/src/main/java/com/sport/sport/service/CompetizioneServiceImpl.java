package com.sport.sport.service;

import com.sport.sport.DTO.CompetizioneDTO;
import com.sport.sport.entity.Competizione;
import com.sport.sport.DAO.CompetizioneDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CompetizioneServiceImpl implements CompetizioneService{
    @Autowired
    private final CompetizioneDAO competizioneRepository;

    public CompetizioneServiceImpl(CompetizioneDAO competizioneRepository) {
        this.competizioneRepository = competizioneRepository;
    }

    public Competizione findId(Long id){return competizioneRepository.findById(id).orElse(null);}

    public Long insert(Competizione e){ return competizioneRepository.save(e).getId();}

    public void delete(Competizione e){ competizioneRepository.delete(e);}

    @Override
    public List<Competizione> findAll() {
        List<Competizione> list = new ArrayList<>();
        competizioneRepository.findAll().forEach((c) -> list.add(c));
        return list;
    }

    @Override
    public Competizione update(CompetizioneDTO c) {
        Competizione ent = competizioneRepository.getById(c.getId());
        ent.setNome(c.getNome());
        return ent;
    }

    @Override
    public boolean isPresent(long id) {
        return competizioneRepository.existsById(id);
    }

    @Override
    public boolean isValid(CompetizioneDTO s) {
        boolean isNomeValid = (s.getNome().isBlank() || s.getNome().isEmpty());
        return !isNomeValid;
    }
}
