package com.sport.sport.DTO;

import com.sport.sport.entity.Allenatore;

public class AllenatoreDTO {
    long id; String nome;

    public AllenatoreDTO(Allenatore a){
        this.id = a.getId();
        this.nome = a.getNome();
    }

    public AllenatoreDTO(){}

    public AllenatoreDTO(String nome){
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "AllenatoreDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
