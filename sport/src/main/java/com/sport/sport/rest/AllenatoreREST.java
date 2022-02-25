package com.sport.sport.rest;

import com.sport.sport.DTO.AllenatoreDTO;
import com.sport.sport.DTO.SquadraDTO;
import com.sport.sport.entity.Allenatore;
import com.sport.sport.entity.Squadra;
import com.sport.sport.service.AllenatoreService;
import com.sport.sport.service.SquadraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/allenatore")
public class AllenatoreREST {
    @Autowired
    AllenatoreService allenatoreService;

    public AllenatoreREST(AllenatoreService allenatoreService) {
        this.allenatoreService = allenatoreService;
    }

    @PostMapping("insert")
    public ResponseEntity<Long> insert(@RequestBody AllenatoreDTO s) {
        try {
            Allenatore entity = new Allenatore(s);
            Long id = allenatoreService.insert(entity);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<AllenatoreDTO> findId(@PathVariable("id") long id){
        try{
            Allenatore entity = allenatoreService.findId(id);
            AllenatoreDTO dto = new AllenatoreDTO(entity);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<AllenatoreDTO>> findAll(){
        try{
            List<Allenatore> entityList = allenatoreService.findAll();
            List<AllenatoreDTO> DTOlist = new ArrayList<>();
            entityList.forEach((a) -> DTOlist.add(new AllenatoreDTO(a)));
            return new ResponseEntity<>(DTOlist, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
