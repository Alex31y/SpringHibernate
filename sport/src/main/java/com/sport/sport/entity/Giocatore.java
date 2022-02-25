package com.sport.sport.entity;

import com.sport.sport.DTO.GiocatoreDTO;

import javax.persistence.*;

@Entity
@Table(name = "Giocatore")
public class Giocatore {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String nome;

    @Column()
    private int presenze;

    @ManyToOne
    @JoinColumn(name="squadra_id", referencedColumnName = "id")
    private Squadra squadra;

    public Giocatore(){}

    public Giocatore(String nome, int presenze, Squadra squadra) {
        this.nome = nome;
        this.presenze = presenze;
        this.squadra = squadra;
    }

    public Giocatore(String nome, int presenze) {
        this.nome = nome;
        this.presenze = presenze;
    }

    public Giocatore(GiocatoreDTO g) {
        this.id = g.getId();
        this.nome = g.getNome();
        this.presenze = g.getPresenze();
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

    public int getPresenze() {
        return presenze;
    }

    public void setPresenze(int presenze) {
        this.presenze = presenze;
    }

    public Squadra getSquadra() {
        return squadra;
    }

    public void setSquadra(Squadra squadra) {
        this.squadra = squadra;
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
