package com.sport.sport.rest;

import com.sport.sport.DTO.GiocatoreDTO;
import com.sport.sport.DTO.SquadraDTO;
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

    /**
     * API per l'inserimento di un nuovo giocatore
     * @param g giocatore da inserire sotto forma di DTO
     * @return l'id assegnato all'entità inserita, 0 in caso di errore
     */
    @PostMapping("insert")
    public ResponseEntity<Long> insert(@RequestBody GiocatoreDTO g) {
        long id = 0;
        try {
            //controlo sul model d'input
            if(giocatoreService.isValid(g)) {
                Giocatore entity = new Giocatore(g);
                id = giocatoreService.insert(entity);

                //controllo sul valore di ritorno
                if(id>0)
                    return new ResponseEntity<>(id, HttpStatus.OK);
            }
            return new ResponseEntity<>(id, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * API per l'update di un'istanza Giocatore già presente nel DB
     * @param g istanza GiocatoreDTO con i valori da aggiornare
     * @return istanza con i dati aggiornati
     */
    @PostMapping("update")
    public ResponseEntity<GiocatoreDTO> update(@RequestBody GiocatoreDTO g){
        try{
            if(giocatoreService.isValid(g) && giocatoreService.exists(g.getId())){
                g = new GiocatoreDTO(giocatoreService.update(g));
                return new ResponseEntity<>(g, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<GiocatoreDTO> findId(@PathVariable("id") Long id){
        try {
            boolean idIsValid = (id != null && id > 0);
            if(idIsValid) {
                Giocatore entity = giocatoreService.findId(id);
                GiocatoreDTO dto = new GiocatoreDTO(entity);
                if(giocatoreService.isValid(dto))
                    return new ResponseEntity<>(dto, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * rimozione di un'istanza giocatore dal DB tramite ID
     * @param id
     * @return
     */
    @GetMapping("/del/{id}")
    public ResponseEntity<String> deleteId(@PathVariable("id") Long id) {
        try {
            boolean idIsValid = (id != null && id > 0);
            //controllo sull'input ID
            if (idIsValid && giocatoreService.exists(id)) {
                giocatoreService.delete(giocatoreService.findId(id));
                return new ResponseEntity<>("Rimozione effettuata con successo", HttpStatus.OK);
            }

            return new ResponseEntity<>("Errore nell'input ID", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Eccezione nella richiesta", HttpStatus.INTERNAL_SERVER_ERROR);
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
}
