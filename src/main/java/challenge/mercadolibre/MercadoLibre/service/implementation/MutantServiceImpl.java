package challenge.mercadolibre.MercadoLibre.service.implementation;
 
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import challenge.mercadolibre.MercadoLibre.dtos.request.MutantRequestDTO;
import challenge.mercadolibre.MercadoLibre.exceptions.NotSquareException;
import challenge.mercadolibre.MercadoLibre.exceptions.WrongLettersException;
import challenge.mercadolibre.MercadoLibre.service.MutantService;

@Service
public class MutantServiceImpl implements MutantService {

    private enum Directions {
        R, D, DR, DL
    };

    @Override
    public Boolean isMutant(MutantRequestDTO mutantRequestDTO) {
        validRequest(mutantRequestDTO);
        String dna[] = mutantRequestDTO.getDna();
        int n = mutantRequestDTO.getDna().length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                char letter = dna[i].charAt(j);

                if (search(dna, i, j, letter, 0, Directions.R) || search(dna, i, j, letter, 0, Directions.D)
                        || search(dna, i, j, letter, 0, Directions.DR) || search(dna, i, j, letter, 0, Directions.DL)) {
                    return true;
                }
            }
        }
        return false;
    }


    private void validRequest(MutantRequestDTO mutantRequestDTO) {
        String chain[] = mutantRequestDTO.getDna();
        int n = chain.length;
        for (int i = 0; i < n; i++) {
            if(!Pattern.matches("^[ACTG]+$", chain[i]))
                throw new WrongLettersException();
            if (chain[i].length() != n)
                throw new NotSquareException();
        }
    }

    private Boolean search(String[] dna, int column, int row, char letter, int count,
            Directions direction) {
        if (count == 3)
            return true;
        switch (direction) {
            case R:
                if (column + 1 < dna.length && dna[row].charAt(column + 1) == letter)
                    return search(dna, column + 1, row, letter, count + 1, Directions.R);
                break;
            case D:
                if (row + 1 < dna.length && dna[row].charAt(column) == letter)
                    return search(dna, column, row + 1, letter, count + 1, Directions.D);
                break;
            case DR:
                if (row + 1 < dna.length && column + 1 < dna.length && dna[row + 1].charAt(column + 1) == letter)
                    return search(dna, column + 1, row + 1, letter, count + 1, Directions.DR);
                break;
            case DL:
                if (row + 1 < dna.length && column - 1 >= 0 && dna[row + 1].charAt(column - 1) == letter)
                    return search(dna, column - 1, row + 1, letter, count + 1, Directions.DL);
                break;
            default:
                break;
        }
        return false;
    }
}
