package com.leoncarraro.breweryapi.service;

import com.leoncarraro.breweryapi.dto.BeerRequest;
import com.leoncarraro.breweryapi.dto.BeerResponse;
import com.leoncarraro.breweryapi.model.Beer;
import com.leoncarraro.breweryapi.model.Style;
import com.leoncarraro.breweryapi.model.enums.Flavor;
import com.leoncarraro.breweryapi.model.enums.Origin;
import com.leoncarraro.breweryapi.repository.BeerRepository;
import com.leoncarraro.breweryapi.repository.StyleRepository;
import com.leoncarraro.breweryapi.service.exceptions.ObjectAlreadyExistsException;
import com.leoncarraro.breweryapi.service.exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BeerService {

    private final BeerRepository beerRepository;
    private final StyleRepository styleRepository;

    @Transactional(readOnly = true)
    public List<BeerResponse> getAllWithFilter(String sku, String name, List<Long> stylesList,
                                               BigDecimal minValue, BigDecimal maxValue) {

        return beerRepository.getAllWithFilter(sku, name, stylesList, minValue, maxValue)
                .stream().map(BeerResponse::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BeerResponse getOneBySku(String sku) {
        Beer beer = beerRepository.findBySku(sku)
                .orElseThrow(() -> new ObjectNotFoundException("Cerveja " + sku + " não encontrada!"));

        return new BeerResponse(beer);
    }

    @Transactional
    public BeerResponse create(BeerRequest beerRequest) {
        if (beerRepository.existsBySku(beerRequest.getSku())) {
            throw new ObjectAlreadyExistsException("Cerveja " + beerRequest.getSku() + " já cadastrada no sistema!");
        }

        Origin origin = Origin.getByDescription(beerRequest.getOrigin())
                .orElseThrow(() -> new ObjectNotFoundException(
                        "Origem " + beerRequest.getOrigin() + " não encontrada!"));

        Flavor flavor = Flavor.getByDescription(beerRequest.getFlavor())
                .orElseThrow(() -> new ObjectNotFoundException(
                        "Sabor " + beerRequest.getFlavor() + " não encontrado!"));

        Style style = styleRepository.findById(beerRequest.getStyleId())
                .orElseThrow(() -> new ObjectNotFoundException(
                        "Estilo de código " + beerRequest.getStyleId() + " não encontrado!"));

        Beer beer = new Beer(beerRequest, origin, flavor, style);
        beer = beerRepository.save(beer);
        return new BeerResponse(beer);
    }

}
