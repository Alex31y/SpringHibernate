package com.sport.sport.DTO;

import com.sport.sport.entity.Competizione;

public class CompetizioneDTO {
    long id; String nome;

    public CompetizioneDTO(Competizione ent){
        this.id = ent.getId();
        this.nome = ent.getNome();
    }

    public CompetizioneDTO(){}

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
        return "CompetizioneDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
