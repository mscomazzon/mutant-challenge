package challenge.mercadolibre.MercadoLibre.unit.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import challenge.mercadolibre.MercadoLibre.dtos.request.MutantRequestDTO;
import challenge.mercadolibre.MercadoLibre.exceptions.BadRequestException;
import challenge.mercadolibre.MercadoLibre.service.implementation.MutantServiceImpl;

@ExtendWith(MockitoExtension.class)
public class MutantServiceTest {

    @InjectMocks
    MutantServiceImpl mutantServiceImpl;

    @Test
    @DisplayName("Should return true, it is a mutant")
    public void shouldReturnTrueDiagonalRight(){
        String dna[] = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        MutantRequestDTO mutantRequestDTO = new MutantRequestDTO();
        mutantRequestDTO.setDna(dna);

        assertTrue(mutantServiceImpl.isMutant(mutantRequestDTO));
    }

    @Test
    @DisplayName("Should return true, it is a mutant")
    public void shouldReturnTrueDiagonalLeft(){
        String dna[] = {"GTCA", "CAAT", "AATA", "AGCC"};
        MutantRequestDTO mutantRequestDTO = new MutantRequestDTO();
        mutantRequestDTO.setDna(dna);

        assertTrue(mutantServiceImpl.isMutant(mutantRequestDTO));
    }

    @Test
    @DisplayName("Should return true, it is a mutant")
    public void shouldReturnTrueDown(){
        String dna[] = {"ATCAACG", "AGCTTCG", "ATCAACG", "ATCAACG", "ATCAACG", "ATCAACG", "ATCAACG"};
        MutantRequestDTO mutantRequestDTO = new MutantRequestDTO();
        mutantRequestDTO.setDna(dna);

        assertTrue(mutantServiceImpl.isMutant(mutantRequestDTO));
    }

    @Test
    @DisplayName("Should return true, it is a mutant")
    public void shouldReturnTrueRight(){
        String dna[] = {"AAAAAAA", "AGCTTCG", "ATCAACG", "ATCAACG", "ATCAACG", "ATCAACG", "ATCAACG"};
        MutantRequestDTO mutantRequestDTO = new MutantRequestDTO();
        mutantRequestDTO.setDna(dna);

        assertTrue(mutantServiceImpl.isMutant(mutantRequestDTO));
    }

    @Test
    @DisplayName("Should return false, it is not a mutant")
    public void shouldReturnFalse(){
        String dna[] = {"ATG", "CGA", "TTA"};
        MutantRequestDTO mutantRequestDTO = new MutantRequestDTO();
        mutantRequestDTO.setDna(dna);

        assertFalse(mutantServiceImpl.isMutant(mutantRequestDTO));
    }

    @Test
    @DisplayName("Should throw an error, it is not nxn")
    public void shouldThrowErrorSquare(){
        String dna[] = {"AT", "CGA", "TTA"};
        MutantRequestDTO mutantRequestDTO = new MutantRequestDTO(); 
        mutantRequestDTO.setDna(dna);

        assertThrows(BadRequestException.class, () -> mutantServiceImpl.isMutant(mutantRequestDTO));
    }

    @Test
    @DisplayName("Should throw an error, it includes a letter different than A, C, T, G")
    public void shouldThrowErrorLetter(){
        String dna[] = {"ATK", "CGA", "TTA"};
        MutantRequestDTO mutantRequestDTO = new MutantRequestDTO(); 
        mutantRequestDTO.setDna(dna);

        assertThrows(BadRequestException.class, () -> mutantServiceImpl.isMutant(mutantRequestDTO));
    }
}
