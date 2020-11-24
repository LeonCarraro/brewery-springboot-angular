package com.leoncarraro.breweryapi.service;

import com.leoncarraro.breweryapi.dto.CityRequest;
import com.leoncarraro.breweryapi.dto.CityResponse;
import com.leoncarraro.breweryapi.dto.mapper.CityMapper;
import com.leoncarraro.breweryapi.model.City;
import com.leoncarraro.breweryapi.model.State;
import com.leoncarraro.breweryapi.repository.CityRepository;
import com.leoncarraro.breweryapi.repository.StateRepository;
import com.leoncarraro.breweryapi.service.exceptions.BadRequestException;
import com.leoncarraro.breweryapi.service.exceptions.ObjectAlreadyExistsException;
import com.leoncarraro.breweryapi.service.exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CityService {

    private final CityMapper cityMapper = CityMapper.INSTANCE;

    private final CityRepository cityRepository;
    private final StateRepository stateRepository;

    @Transactional(readOnly = true)
    public List<CityResponse> getAllByState(Long stateId) {
        return cityRepository.getAllByState(stateId)
                .stream()
                .map(cityMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CityResponse getOneById(Long id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Cidade de código " + id + " não encontrada!"));

        return cityMapper.toResponse(city);
    }

    @Transactional
    public CityResponse create(CityRequest cityRequest) {
        if (cityRepository.existsByName(cityRequest.getName())) {
            throw new ObjectAlreadyExistsException("Cidade " + cityRequest.getName() + " já cadastrada no sistema!");
        }

        State state = stateRepository.findById(cityRequest.getStateId())
                .orElseThrow(() -> new BadRequestException(
                        "Cidade de código " + cityRequest.getStateId() + " não encontrada!"));

        City city = cityMapper.toModel(cityRequest, state);
        city = cityRepository.save(city);
        return cityMapper.toResponse(city);
    }

}
