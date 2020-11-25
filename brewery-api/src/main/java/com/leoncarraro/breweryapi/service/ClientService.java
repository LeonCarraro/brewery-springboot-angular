package com.leoncarraro.breweryapi.service;

import com.leoncarraro.breweryapi.dto.ClientRequest;
import com.leoncarraro.breweryapi.dto.ClientResponse;
import com.leoncarraro.breweryapi.dto.mapper.ClientMapper;
import com.leoncarraro.breweryapi.model.City;
import com.leoncarraro.breweryapi.model.Client;
import com.leoncarraro.breweryapi.model.enums.ClientType;
import com.leoncarraro.breweryapi.repository.CityRepository;
import com.leoncarraro.breweryapi.repository.ClientRepository;
import com.leoncarraro.breweryapi.service.exceptions.BadRequestException;
import com.leoncarraro.breweryapi.service.exceptions.ObjectAlreadyExistsException;
import com.leoncarraro.breweryapi.service.exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientMapper clientMapper = ClientMapper.INSTANCE;

    private final ClientRepository clientRepository;
    private final CityRepository cityRepository;

    @Transactional(readOnly = true)
    public Page<ClientResponse> getAllWithFilterAndPagination(PageRequest pageRequest, String name, String document) {
        return clientRepository.getAllWithFilterAndPagination(pageRequest, name, document)
                .map(clientMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public ClientResponse getOneById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Cliente de código " + id + " não encontrado!"));

        return clientMapper.toResponse(client);
    }

    @Transactional
    public ClientResponse create(ClientRequest clientRequest) {
        if (clientRepository.existsByDocument(clientRequest.getDocument())) {
            throw new ObjectAlreadyExistsException(
                    "CPF/CNPJ " + clientRequest.getDocument() + " já cadastrado no sistema!");
        }

        ClientType clientType = ClientType.getByDescription(clientRequest.getClientType())
                .orElseThrow(() -> new BadRequestException(
                        "Tipo de cliente '" + clientRequest.getClientType() + "' não encontrado!"));

        City city = cityRepository.findById(clientRequest.getCityId())
                .orElseThrow(() -> new BadRequestException(
                        "Cidade de código " + clientRequest.getCityId() + " não encontrada!"));

        Client client = clientMapper.toModel(clientRequest, clientType, city);
        client = clientRepository.save(client);
        return clientMapper.toResponse(client);
    }

}
