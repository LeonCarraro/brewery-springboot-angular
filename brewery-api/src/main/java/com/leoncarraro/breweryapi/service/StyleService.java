package com.leoncarraro.breweryapi.service;

import com.leoncarraro.breweryapi.dto.StyleRequest;
import com.leoncarraro.breweryapi.dto.StyleResponse;
import com.leoncarraro.breweryapi.dto.mapper.StyleMapper;
import com.leoncarraro.breweryapi.model.Style;
import com.leoncarraro.breweryapi.repository.StyleRepository;
import com.leoncarraro.breweryapi.service.exceptions.ObjectAlreadyExistsException;
import com.leoncarraro.breweryapi.service.exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StyleService {

    private final StyleMapper styleMapper = StyleMapper.INSTANCE;

    private final StyleRepository styleRepository;

    @Transactional(readOnly = true)
    public List<StyleResponse> getAll() {
        return styleRepository.findAll()
                .stream()
                .map(styleMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public StyleResponse getOneById(Long id) {
        Style style = styleRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Estilo de código " + id + " não encontrado!"));

        return styleMapper.toResponse(style);
    }

    @Transactional
    public StyleResponse create(StyleRequest styleRequest) {
        if (styleRepository.existsByName(styleRequest.getName())) {
            throw new ObjectAlreadyExistsException("Estilo " + styleRequest.getName() + " já cadastrado no sistema!");
        }

        Style style = styleMapper.toModel(styleRequest);
        style = styleRepository.save(style);
        return styleMapper.toResponse(style);
    }

}
