package com.sport.sport;

import com.sport.sport.entity.Competizione;
import com.sport.sport.entity.Giocatore;
import com.sport.sport.entity.Squadra;
import com.sport.sport.rest.SquadraREST;
import com.sport.sport.service.AllenatoreServiceImpl;
import com.sport.sport.service.CompetizioneServiceImpl;
import com.sport.sport.service.GiocatoreService;
import com.sport.sport.service.SquadraServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertThat;

@SpringBootTest
public class test1 {
    @Autowired
    private SquadraREST controller;

    @Autowired
    private SquadraServiceImpl squadraService;

    @Autowired
    private GiocatoreService giocatoreService;

    @Autowired
    private AllenatoreServiceImpl allenatoreService;

    @Autowired
    private CompetizioneServiceImpl competizioneService;

    /*
    @Test
    public void test1aN(){
        //prendo una squadra esistente (Napoli 3L) e vi aggiungo dei nuovi giocatori
        Squadra napoli = new Squadra("nabolee", 300);
        Giocatore g1 = new Giocatore("AndonGiulio", 6);
        Giocatore g2 = new Giocatore("Albertonzolo", 2);
        Giocatore g3 = new Giocatore("PierFranco", 8);

        //aggiungo la relazione tra il napoli e g1g2g3
        squadraService.insert(napoli);
        napoli.addGiocatore(g1);
        napoli.addGiocatore(g2);
        napoli.addGiocatore(g3);
        giocatoreService.insert(g1);
        giocatoreService.insert(g2);
        giocatoreService.insert(g3);

        //stampo i giocatori associati al napoli
        System.out.println("Lista giocatori del " + napoli.getNome());
        napoli.getGiocatori().forEach((g) -> System.out.println(g));
    }*/

    @Test
    public void enneToN(){
        Competizione c1 = new Competizione("coppa del trapezio isoscele");
        Squadra s1 = new Squadra("Polisportiva Albinoleffe", 16);

        competizioneService.insert(c1);
        s1.addCompetizione(c1);
        squadraService.insert(s1);
    }

}
