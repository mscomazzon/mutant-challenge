package challenge.mercadolibre.MercadoLibre.service;

import challenge.mercadolibre.MercadoLibre.dtos.request.MutantRequestDTO;

public interface MutantService {
    Boolean isMutant(MutantRequestDTO mutantRequestDTO);
}
