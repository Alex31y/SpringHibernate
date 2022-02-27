package com.sport.sport.rest;

import com.sport.sport.DTO.SquadraDTO;
import com.sport.sport.entity.Squadra;
import com.sport.sport.service.SquadraService;
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

    /**
     * API per l'inserimento di una nuova squadra
     * @param s squadra da inserire sotto forma di DTO
     * @return l'id assegnato alla squadra inserita, 0 in caso di errore
     */
    @PostMapping("insert")
    public ResponseEntity<Long> insert(@RequestBody SquadraDTO s) {
        long id = 0;
        try {
            //controllo sul model d'input
            if(squadraService.isValid(s)) {
                Squadra squad = new Squadra(s);
                id = squadraService.insert(squad);

                //controllo sul valore di ritorno
                if(id > 0)
                    return new ResponseEntity<>(id, HttpStatus.OK);
            }

            return new ResponseEntity<>(id, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * API per l'update di un'istanza Squadra già presente nel DB
     * @param s istanza SquadraDTO con i valori da aggiornare
     * @return istanza con i dati aggiornati
     */
    @PostMapping("update")
    public ResponseEntity<SquadraDTO> update(@RequestBody SquadraDTO s){
        try{
            if(squadraService.isValid(s) && squadraService.isPresent(s.getId())){
                s = new SquadraDTO(squadraService.update(s));
                return new ResponseEntity<>(s, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Ricerca di una squadra tramite ID
     * @param id
     * @return oggetto tipo model della squadra trovata
     */
    @GetMapping("/find/{id}")
    public ResponseEntity<SquadraDTO> findId(@PathVariable("id") Long id) {
        try {
            boolean idIsValid = (id != null && id > 0);
            //controllo sull'input ID
            if (idIsValid) {
                Squadra found = squadraService.findId(id);
                SquadraDTO squad = new SquadraDTO(found);
                if(squadraService.isValid(squad))
                    return new ResponseEntity<>(squad, HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * rimozione di un'istanza squadra dal DB tramite ID
     * @param id
     * @return
     */
    @GetMapping("/del/{id}")
    public ResponseEntity<String> deleteId(@PathVariable("id") Long id) {
        try {
            boolean idIsValid = (id != null && id > 0);
            //controllo sull'input ID
            if (idIsValid && squadraService.isPresent(id)) {
                squadraService.delete(squadraService.findId(id));
                return new ResponseEntity<>("Rimozione effettuata con successo", HttpStatus.OK);
            }

            return new ResponseEntity<>("Errore nell'input ID", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Eccezione nella richiesta", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * richiesta di tutte le squadre presenti nel DB
     * @return list contenente tutte le squadre sotto forma di DTO
     */
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

    /**
     * API per ottenere la squadra dal numero più alto di vittorie
     * @return DTO della squadra con numero più alto di vittorie
     */
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
