package com.leoncarraro.breweryapi.service;

import com.leoncarraro.breweryapi.dto.BeerRequest;
import com.leoncarraro.breweryapi.dto.BeerResponse;
import com.leoncarraro.breweryapi.dto.mapper.BeerMapper;
import com.leoncarraro.breweryapi.model.Beer;
import com.leoncarraro.breweryapi.model.Style;
import com.leoncarraro.breweryapi.model.enums.Flavor;
import com.leoncarraro.breweryapi.model.enums.Origin;
import com.leoncarraro.breweryapi.repository.BeerRepository;
import com.leoncarraro.breweryapi.repository.StyleRepository;
import com.leoncarraro.breweryapi.service.exceptions.BadRequestException;
import com.leoncarraro.breweryapi.service.exceptions.ObjectAlreadyExistsException;
import com.leoncarraro.breweryapi.service.exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@Service
@AllArgsConstructor
public class BeerService {

    private final BeerMapper beerMapper = BeerMapper.INSTANCE;

    private final BeerRepository beerRepository;
    private final StyleRepository styleRepository;
    private final S3Service s3Service;
    private final ImageService imageService;

    @Transactional(readOnly = true)
    public Page<BeerResponse> getAllWithFilterAndPagination(PageRequest pageRequest, String sku, String name,
                                                            List<Long> stylesList, BigDecimal minValue,
                                                            BigDecimal maxValue) {

        return beerRepository.getAllWithFilterAndPagination(pageRequest, sku, name, stylesList, minValue, maxValue)
                .map(beerMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public BeerResponse getOneBySku(String sku) {
        Beer beer = beerRepository.findBySku(sku)
                .orElseThrow(() -> new ObjectNotFoundException("Cerveja " + sku + " não encontrada!"));

        return beerMapper.toResponse(beer);
    }

    @Transactional
    public BeerResponse create(BeerRequest beerRequest) {
        if (beerRepository.existsBySku(beerRequest.getSku())) {
            throw new ObjectAlreadyExistsException("Cerveja " + beerRequest.getSku() + " já cadastrada no sistema!");
        }

        Origin origin = Origin.getByDescription(beerRequest.getOrigin())
                .orElseThrow(() -> new BadRequestException(
                        "Origem " + beerRequest.getOrigin() + " não encontrada!"));

        Flavor flavor = Flavor.getByDescription(beerRequest.getFlavor())
                .orElseThrow(() -> new BadRequestException(
                        "Sabor " + beerRequest.getFlavor() + " não encontrado!"));

        Style style = styleRepository.findById(beerRequest.getStyleId())
                .orElseThrow(() -> new BadRequestException(
                        "Estilo de código " + beerRequest.getStyleId() + " não encontrado!"));

        Beer beer = beerMapper.toModel(beerRequest, origin, flavor, style);
        beer = beerRepository.save(beer);
        return beerMapper.toResponse(beer);
    }

    @Transactional
    public URI uploadImage(MultipartFile multipartFile, String sku) {
        Beer beer = beerRepository.findBySku(sku)
                .orElseThrow(() -> new ObjectNotFoundException("Cerveja " + sku + " não encontrada!"));

        BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
        jpgImage = imageService.resize(jpgImage, 200);
        String filename = "B-" + beer.getSku() + ".jpg";

        URI uri = s3Service.uploadFile(filename, "image", imageService.getInputStream(jpgImage, "jpg"));

        beer.setImagePath(uri.toString());
        beerRepository.save(beer);

        return uri;
    }

}
