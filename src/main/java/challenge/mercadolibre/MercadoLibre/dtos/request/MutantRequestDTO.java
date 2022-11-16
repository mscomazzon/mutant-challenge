package challenge.mercadolibre.MercadoLibre.dtos.request;

import javax.validation.Valid;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MutantRequestDTO {

    @Valid
    private String[] dna; 
}
