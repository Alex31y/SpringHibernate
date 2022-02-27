package com.sport.sport.rest;

import com.sport.sport.DTO.CompetizioneDTO;
import com.sport.sport.DTO.SquadraDTO;
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

    /**
     * API per l'inserimento di una nuova competizione
     * @param c competizione da inserire sotto forma di DTO
     * @return l'id assegnato alla competizione inserita, 0 in caso di errore
     */
    @PostMapping("insert")
    public ResponseEntity<Long> insert(@RequestBody CompetizioneDTO c){
        long id = 0;
        try{
            //controllo sul model d'input
            if(competizioneService.isValid(c)) {
                Competizione entity = new Competizione(c);
                id = competizioneService.insert(entity);

                //controllo sul valore di ritorno
                if(id>0)
                    return new ResponseEntity<>(id, HttpStatus.OK);
            }
            return new ResponseEntity<>(id, HttpStatus.BAD_REQUEST);
        } catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * API per l'update di un'istanza Competizione già presente nel DB
     * @param c istanza CompetizioneDTO con i valori da aggiornare
     * @return istanza con i dati aggiornati
     */
    @PostMapping("update")
    public ResponseEntity<CompetizioneDTO> update(@RequestBody CompetizioneDTO c){
        try{
            if(competizioneService.isValid(c) && competizioneService.isPresent(c.getId())){
                c = new CompetizioneDTO(competizioneService.update(c));
                return new ResponseEntity<>(c, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<CompetizioneDTO> findId(@PathVariable("id") Long id){
        try{
            boolean idIsValid = (id != null && id > 0);
            if(idIsValid) {
                Competizione entity = competizioneService.findId(id);
                CompetizioneDTO dto = new CompetizioneDTO(entity);
                if(competizioneService.isValid(dto))
                    return new ResponseEntity<>(dto, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * rimozione di un'istanza competizione dal DB tramite ID
     * @param id
     * @return
     */
    @GetMapping("/del/{id}")
    public ResponseEntity<String> deleteId(@PathVariable("id") Long id) {
        try {
            boolean idIsValid = (id != null && id > 0);
            //controllo sull'input ID
            if (idIsValid && competizioneService.isPresent(id)) {
                competizioneService.delete(competizioneService.findId(id));
                return new ResponseEntity<>("Rimozione effettuata con successo", HttpStatus.OK);
            }

            return new ResponseEntity<>("Errore nell'input ID", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Eccezione nella richiesta", HttpStatus.INTERNAL_SERVER_ERROR);
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
