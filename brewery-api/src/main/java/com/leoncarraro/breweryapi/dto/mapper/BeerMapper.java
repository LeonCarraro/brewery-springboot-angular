package com.leoncarraro.breweryapi.dto.mapper;

import com.leoncarraro.breweryapi.dto.BeerRequest;
import com.leoncarraro.breweryapi.dto.BeerResponse;
import com.leoncarraro.breweryapi.model.Beer;
import com.leoncarraro.breweryapi.model.Style;
import com.leoncarraro.breweryapi.model.enums.Flavor;
import com.leoncarraro.breweryapi.model.enums.Origin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { OriginMapper.class, FlavorMapper.class })
public interface BeerMapper {

    BeerMapper INSTANCE = Mappers.getMapper(BeerMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "imagePath", ignore = true)
    @Mapping(source = "beerRequest.name", target = "name")
    @Mapping(source = "beerRequest.description", target = "description")
    @Mapping(source = "origin", target = "origin")
    @Mapping(source = "flavor", target = "flavor")
    Beer toModel(BeerRequest beerRequest, Origin origin, Flavor flavor, Style style);

    @Mapping(source = "beer.style.id", target = "styleResponse.id")
    @Mapping(source = "beer.style.name", target = "styleResponse.name")
    BeerResponse toResponse(Beer beer);

}
