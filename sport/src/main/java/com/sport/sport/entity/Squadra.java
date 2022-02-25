package com.sport.sport.entity;

import com.sport.sport.DTO.SquadraDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Squadra")
public class Squadra {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private int vittorie;

    @OneToOne(mappedBy = "squadra") //ma in che senso
    private Allenatore allenatore;

    @OneToMany(mappedBy = "squadra", fetch = FetchType.EAGER)
    private List<Giocatore> giocatori;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "squadra_competizione",
            joinColumns = { @JoinColumn(name = "fk_squadra") },
            inverseJoinColumns = { @JoinColumn(name="fk_competizione") })
    private Set<Competizione> competizioni = new HashSet<>(); //più efficiente della List

    public Squadra(){}

    public Squadra(String nome, int vittorie) {
        this.nome = nome;
        this.vittorie = vittorie;
        this.giocatori = new ArrayList<>();
    }

    public Squadra(SquadraDTO s){
        this.nome = s.getNome();
        this.vittorie = s.getVittore();
        this.giocatori = new ArrayList<>();
    }

    public void addCompetizione(Competizione comp){
        this.competizioni.add(comp);
    }

    public void rmvCompetizione(Competizione comp){
        this.competizioni.remove(comp);
    }

    public Set<Competizione> getCompetizioni() {
        return competizioni;
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

    public int getVittorie() {
        return vittorie;
    }

    public void setVittorie(int vittorie) {
        this.vittorie = vittorie;
    }

    public Allenatore getAllenatore() {
        return allenatore;
    }

    public void setAllenatore(Allenatore allenatore) {
        this.allenatore = allenatore;
    }

    public List<Giocatore> getGiocatori() {
        return giocatori;
    }

    public void setGiocatori(List<Giocatore> giocatori) {
        this.giocatori = giocatori;
    }

    //coupling con giocatoreDTO
    public void addGiocatore(Giocatore g){
        this.giocatori.add(g);
        g.setSquadra(this);
    }

    @Override
    public String toString() {
        return "SquadraDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", vittorie=" + vittorie +
                '}';
    }
}
