package challenge.mercadolibre.MercadoLibre.unit.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import challenge.mercadolibre.MercadoLibre.controller.MutantController;
import challenge.mercadolibre.MercadoLibre.dtos.request.MutantRequestDTO;
import challenge.mercadolibre.MercadoLibre.exceptions.BadRequestException;
import challenge.mercadolibre.MercadoLibre.service.MutantService;

@ExtendWith(MockitoExtension.class)
public class MutantControllerTest {

    @Mock
    MutantService mutantService;

    @InjectMocks
    MutantController mutantController;
    
    @Test
    @DisplayName("Should return 200 OK")
    public void shouldBeOk(){
        String dna[] = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        MutantRequestDTO mutantRequestDTO = new MutantRequestDTO();
        mutantRequestDTO.setDna(dna);

        when(mutantService.isMutant(mutantRequestDTO)).thenReturn(true);
        ResponseEntity<String> response = mutantController.postMutant(mutantRequestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Should return 403 Forbidden")
    public void shouldBeFrobidden(){
        String dna[] = {"CTGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        MutantRequestDTO mutantRequestDTO = new MutantRequestDTO();
        mutantRequestDTO.setDna(dna);

        when(mutantService.isMutant(mutantRequestDTO)).thenReturn(false);
        ResponseEntity<String> response = mutantController.postMutant(mutantRequestDTO);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    @DisplayName("Should return 404 Bad Request")
    public void shouldBeBadRequestLetter(){
        String dna[] = {"KTGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        MutantRequestDTO mutantRequestDTO = new MutantRequestDTO();
        mutantRequestDTO.setDna(dna);

        when(mutantService.isMutant(mutantRequestDTO)).thenThrow(new BadRequestException("There is a letter different than A, T, C or G"));
        ResponseEntity<String> response = mutantController.postMutant(mutantRequestDTO);

        assertEquals("There is a letter different than A, T, C or G", response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @DisplayName("Should return 404 Bad Request")
    public void shouldBeBadRequestSquare(){
        String dna[] = {"KTG", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        MutantRequestDTO mutantRequestDTO = new MutantRequestDTO();
        mutantRequestDTO.setDna(dna);

        when(mutantService.isMutant(mutantRequestDTO)).thenThrow(new BadRequestException("The matrix must be square nxn"));
        ResponseEntity<String> response = mutantController.postMutant(mutantRequestDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

}
