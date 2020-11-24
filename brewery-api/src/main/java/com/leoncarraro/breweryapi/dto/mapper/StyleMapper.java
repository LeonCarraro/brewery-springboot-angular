package com.leoncarraro.breweryapi.dto.mapper;

import com.leoncarraro.breweryapi.dto.StyleRequest;
import com.leoncarraro.breweryapi.dto.StyleResponse;
import com.leoncarraro.breweryapi.model.Style;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StyleMapper {

    StyleMapper INSTANCE = Mappers.getMapper(StyleMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "beers", ignore = true)
    Style toModel(StyleRequest styleRequest);

    StyleResponse toResponse(Style style);

}
