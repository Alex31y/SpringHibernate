package com.sport.sport.DTO;

import com.sport.sport.entity.Giocatore;

public class GiocatoreDTO {
    long id; String nome; int presenze;

    public GiocatoreDTO(){}

    public GiocatoreDTO(Giocatore g){
        this.id = g.getId();
        this.nome = g.getNome();
        this.presenze = g.getPresenze();
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

    public int getPresenze() {
        return presenze;
    }

    public void setPresenze(int presenze) {
        this.presenze = presenze;
    }

    @Override
    public String toString() {
        return "GiocatoreDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", presenze=" + presenze +
                '}';
    }
}
