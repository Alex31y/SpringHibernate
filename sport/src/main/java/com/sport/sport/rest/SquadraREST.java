package com.sport.sport.rest;

import antlr.StringUtils;
import com.sport.sport.DTO.SquadraDTO;
import com.sport.sport.entity.Squadra;
import com.sport.sport.service.SquadraService;
import com.sport.sport.service.SquadraServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/squadra")
public class SquadraREST {
    @Autowired
    private final SquadraService squadraService;

    public SquadraREST(SquadraService squadraService) {
        this.squadraService = squadraService;
    }

    @PostMapping("insert")
    public ResponseEntity<Long> insert(@RequestBody SquadraDTO s) {
        long id = 0;
        try {
            boolean nomeIsPresent = s.getNome().isBlank();

            if(!nomeIsPresent) {
                Squadra squad = new Squadra(s);
                id = squadraService.insert(squad);
                return new ResponseEntity<>(id, HttpStatus.OK);
            }

            return new ResponseEntity<>(id, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<SquadraDTO> findId(@PathVariable("id") Long id) {
        try {
            Squadra found = squadraService.findId(id);
            SquadraDTO squad = new SquadraDTO(found);
            return new ResponseEntity<>(squad, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/del/{id}")
    public void deleteId(@PathVariable("id") Long id) {
        try {
            squadraService.delete(squadraService.findId(id));
        } catch (Exception e) {
            System.out.println("è successo qualcosa di grave");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<SquadraDTO>> getAll(){
        try{
            List<Squadra> listSquads = squadraService.getALLSquadre();
            List<SquadraDTO> listDTO = new ArrayList<>();
            listSquads.forEach((el) -> listDTO.add(new SquadraDTO(el)));
            return new ResponseEntity<>(listDTO, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/top")
    public ResponseEntity<SquadraDTO> getTopVincite() {
        try {
            Squadra s = squadraService.getTopVincite();
            SquadraDTO dto = new SquadraDTO(s);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
