package com.leoncarraro.breweryapi.dto.mapper;

import com.leoncarraro.breweryapi.dto.ClientRequest;
import com.leoncarraro.breweryapi.dto.ClientResponse;
import com.leoncarraro.breweryapi.model.City;
import com.leoncarraro.breweryapi.model.Client;
import com.leoncarraro.breweryapi.model.enums.ClientType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { ClientTypeMapper.class })
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "clientRequest.name", target = "name")
    @Mapping(source = "clientType", target = "clientType")
    @Mapping(source = "clientRequest.cep", target = "address.cep")
    @Mapping(source = "clientRequest.number", target = "address.number")
    @Mapping(source = "clientRequest.complement", target = "address.complement")
    @Mapping(source = "city", target = "address.city")
    Client toModel(ClientRequest clientRequest, ClientType clientType, City city);

    @Mapping(source = "client.address.cep", target = "cep")
    @Mapping(source = "client.address.number", target = "number")
    @Mapping(source = "client.address.complement", target = "complement")
    @Mapping(source = "client.address.city.id", target = "cityId")
    @Mapping(source = "client.address.city.state.id", target = "stateId")
    ClientResponse toResponse(Client client);

}
