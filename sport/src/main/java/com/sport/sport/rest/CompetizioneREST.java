package com.sport.sport.rest;

import com.sport.sport.DTO.CompetizioneDTO;
import com.sport.sport.entity.Competizione;
import com.sport.sport.service.CompetizioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/competizione")
public class CompetizioneREST {
    @Autowired
    private final CompetizioneService competizioneService;

    public CompetizioneREST(CompetizioneService competizioneService){
        this.competizioneService = competizioneService;
    }

    @PostMapping("insert")
    public ResponseEntity<Long> insert(@RequestBody CompetizioneDTO c){
        try{
            Competizione entity = new Competizione(c);
            Long id = competizioneService.insert(entity);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<CompetizioneDTO> findId(@PathVariable("id") long id){
        try{
            Competizione entity = competizioneService.findId(id);
            CompetizioneDTO dto = new CompetizioneDTO(entity);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("all")
    public ResponseEntity<List<CompetizioneDTO>> findAll(){
        try{
            List<Competizione> entityList = competizioneService.findAll();
            List<CompetizioneDTO> DTOlist = new ArrayList<>();
            entityList.forEach((c) -> DTOlist.add(new CompetizioneDTO(c)));
            return new ResponseEntity<>(DTOlist, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
