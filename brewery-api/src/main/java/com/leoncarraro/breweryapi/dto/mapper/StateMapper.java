package com.leoncarraro.breweryapi.dto.mapper;

import com.leoncarraro.breweryapi.dto.StateRequest;
import com.leoncarraro.breweryapi.dto.StateResponse;
import com.leoncarraro.breweryapi.model.State;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StateMapper {

    StateMapper INSTANCE = Mappers.getMapper(StateMapper.class);

    State toModel(StateRequest stateRequest);

    StateResponse toResponse(State state);

}
