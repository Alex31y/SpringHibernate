package com.sport.sport.entity;

import com.sport.sport.DTO.AllenatoreDTO;

import javax.persistence.*;

@Entity
@Table(name = "Allenatore")
public class Allenatore {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String nome;

    @OneToOne()
    @JoinColumn(name = "squadra_id", referencedColumnName = "id")
    private Squadra squadra;

    public Allenatore(){}

    public Allenatore(AllenatoreDTO dto) {
        this.nome = dto.getNome();
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

    public Squadra getSquadra() {
        return squadra;
    }

    public void setSquadra(Squadra squadra) {
        this.squadra = squadra;
    }

    @Override
    public String toString() {
        return "AllenatoreDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
