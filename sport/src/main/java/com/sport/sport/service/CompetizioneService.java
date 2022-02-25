package com.sport.sport.service;

import com.sport.sport.entity.Competizione;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompetizioneService {
    public Competizione findId(Long id);
    public Long insert(Competizione e);
    public void delete(Competizione e);
    public List<Competizione> findAll();
}
