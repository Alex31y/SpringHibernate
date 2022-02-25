package com.sport.sport.DTO;

import com.sport.sport.entity.Squadra;

public class SquadraDTO {
    long id; String nome; int vittore;
    public SquadraDTO(Squadra s){
        this.nome = s.getNome();
        this.id = s.getId();
        this.vittore = s.getVittorie();
    }

    public SquadraDTO(String nome, int vittorie) {
        this.nome = nome;
        this.vittore = vittore;
    }

    public SquadraDTO(){}

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

    public int getVittore() {
        return vittore;
    }

    public void setVittore(int vittore) {
        this.vittore = vittore;
    }

    @Override
    public String toString() {
        return "SquadraDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", vittore=" + vittore +
                '}';
    }
}
