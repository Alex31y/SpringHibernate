package com.sport.sport.entity;

import com.sport.sport.DTO.CompetizioneDTO;

import javax.persistence.*;

@Entity
@Table(name = "Competizione")
public class Competizione {
    @Id
    @GeneratedValue
    private long id;

    private String nome;

    public Competizione(){}

    public Competizione(String nome) {
        this.nome = nome;
    }

    public Competizione(CompetizioneDTO c) {
        this.nome = c.getNome();
    }

    public long getId() {
        return id;
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
