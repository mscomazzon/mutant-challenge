package challenge.mercadolibre.MercadoLibre.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import challenge.mercadolibre.MercadoLibre.dtos.request.MutantRequestDTO;
import challenge.mercadolibre.MercadoLibre.exceptions.BadRequestException;
import challenge.mercadolibre.MercadoLibre.service.MutantService;

@RestController
public class MutantController {

    @Autowired
    private MutantService mutantService; 

    @GetMapping("/helloWorld")
    public ResponseEntity<String> helloWorld(){
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }

    @PostMapping("/mutant")
    public ResponseEntity<String> postMutant(@RequestBody @Valid MutantRequestDTO mutantRequestDTO){
        try{
            if(this.mutantService.isMutant(mutantRequestDTO)) 
                return new ResponseEntity<>(HttpStatus.OK);
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }catch(BadRequestException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}