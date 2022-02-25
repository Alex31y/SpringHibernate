package com.sport.sport.service;

import com.sport.sport.entity.Allenatore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AllenatoreService {
    public Allenatore findId(Long id);
    public Long insert(Allenatore e);
    public void delete(Allenatore e);
    public List<Allenatore> findAll();
}
