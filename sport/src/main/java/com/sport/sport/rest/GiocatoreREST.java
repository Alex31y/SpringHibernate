package com.sport.sport.rest;

import com.sport.sport.DTO.GiocatoreDTO;
import com.sport.sport.entity.Giocatore;
import com.sport.sport.service.GiocatoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/giocatore")
public class GiocatoreREST {
    @Autowired
    GiocatoreService giocatoreService;

    public GiocatoreREST(GiocatoreService giocatoreService) {
        this.giocatoreService = giocatoreService;
    }

    @PostMapping("insert")
    public ResponseEntity<Long> insert(@RequestBody GiocatoreDTO g) {
        try {
            Giocatore entity = new Giocatore(g);
            Long id = giocatoreService.insert(entity);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<GiocatoreDTO> findId(@PathVariable("id") long id){
        try {
            Giocatore entity = giocatoreService.findId(id);
            GiocatoreDTO dto = new GiocatoreDTO(entity);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<GiocatoreDTO>> findAll(){
        try{
            List<Giocatore> entityList = giocatoreService.findAll();
            List<GiocatoreDTO> DTOlist = new ArrayList<>();
            entityList.forEach(
                    (g) -> DTOlist.add(new GiocatoreDTO(g))
            );
            return new ResponseEntity<>(DTOlist, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<Long> update(@RequestBody GiocatoreDTO g){

        //if( g.getId()!=null  && (g.getPresenze() || ))

        long id;
        try{
            //input obbligatori
            if(giocatoreService.exists(g.getId())){
                id = giocatoreService.insert(new Giocatore(g));
            } else id = 0;
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
