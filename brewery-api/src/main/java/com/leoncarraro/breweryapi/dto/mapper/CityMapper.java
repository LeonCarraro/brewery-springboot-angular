package com.leoncarraro.breweryapi.dto.mapper;

import com.leoncarraro.breweryapi.dto.CityRequest;
import com.leoncarraro.breweryapi.dto.CityResponse;
import com.leoncarraro.breweryapi.model.City;
import com.leoncarraro.breweryapi.model.State;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CityMapper {

    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "cityRequest.name", target = "name")
    @Mapping(source = "state", target = "state")
    City toModel(CityRequest cityRequest, State state);

    CityResponse toResponse(City city);

}
