package com.leoncarraro.breweryapi.service;

import com.leoncarraro.breweryapi.dto.StateRequest;
import com.leoncarraro.breweryapi.dto.StateResponse;
import com.leoncarraro.breweryapi.dto.mapper.StateMapper;
import com.leoncarraro.breweryapi.model.State;
import com.leoncarraro.breweryapi.repository.StateRepository;
import com.leoncarraro.breweryapi.service.exceptions.ObjectAlreadyExistsException;
import com.leoncarraro.breweryapi.service.exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StateService {

    private final StateMapper stateMapper = StateMapper.INSTANCE;

    private final StateRepository stateRepository;

    @Transactional(readOnly = true)
    public List<StateResponse> getAll() {
        return stateRepository.findAll()
                .stream()
                .map(stateMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public StateResponse getOneById(Long id) {
        State state = stateRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Estado de código " + id + " não encontrado!"));

        return stateMapper.toResponse(state);
    }

    @Transactional
    public StateResponse create(StateRequest stateRequest) {
        if (stateRepository.existsByName(stateRequest.getName())) {
            throw new ObjectAlreadyExistsException("Estado " + stateRequest.getName() + " já cadastrado no sistema!");
        }

        State state = stateMapper.toModel(stateRequest);
        state = stateRepository.save(state);
        return stateMapper.toResponse(state);
    }

}
