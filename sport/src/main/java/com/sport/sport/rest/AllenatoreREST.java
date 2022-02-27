package com.sport.sport.rest;

import com.sport.sport.DTO.AllenatoreDTO;
import com.sport.sport.entity.Allenatore;
import com.sport.sport.service.AllenatoreService;
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
        long id = 0;
        try {
            //controllo sul model d'input
            if(allenatoreService.isValid(s)) {
                Allenatore entity = new Allenatore(s);
                id = allenatoreService.insert(entity);
                if(id>0)
                    return new ResponseEntity<>(id, HttpStatus.OK);
            }
            return new ResponseEntity<>(id, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * API per l'update di un'istanza Allenatore già presente nel DB
     * @param a istanza AllenatoreDTO con i valori da aggiornare
     * @return istanza con i dati aggiornati
     */
    @PostMapping("update")
    public ResponseEntity<AllenatoreDTO> update(@RequestBody AllenatoreDTO a){
        try{
            if(allenatoreService.isValid(a) && allenatoreService.isPresent(a.getId())){
                a = new AllenatoreDTO(allenatoreService.update(a));
                return new ResponseEntity<>(a, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<AllenatoreDTO> findId(@PathVariable("id") Long id){
        try{
            boolean idIsValid = (id != null && id > 0);
            if(idIsValid) {
                Allenatore entity = allenatoreService.findId(id);
                AllenatoreDTO dto = new AllenatoreDTO(entity);
                if(allenatoreService.isValid(dto))
                    return new ResponseEntity<>(dto, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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

    /**
     * rimozione di un'istanza allenatore dal DB tramite ID
     * @param id
     * @return
     */
    @GetMapping("/del/{id}")
    public ResponseEntity<String> deleteId(@PathVariable("id") Long id) {
        try {
            boolean idIsValid = (id != null && id > 0);
            //controllo sull'input ID
            if (idIsValid && allenatoreService.isPresent(id)) {
                allenatoreService.delete(allenatoreService.findId(id));
                return new ResponseEntity<>("Rimozione effettuata con successo", HttpStatus.OK);
            }

            return new ResponseEntity<>("Errore nell'input ID", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Eccezione nella richiesta", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
