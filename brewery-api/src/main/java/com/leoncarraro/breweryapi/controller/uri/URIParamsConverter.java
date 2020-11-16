package com.leoncarraro.breweryapi.controller.uri;

import com.leoncarraro.breweryapi.service.exceptions.BadRequestException;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class URIParamsConverter {

    public static List<Long> getList(String ids) {
        List<Long> styles = new ArrayList<>();

        if (StringUtils.hasText(ids)) {
            try {
                List<String> stylesAsString = Arrays.asList(ids.split(","));
                stylesAsString.forEach(s -> styles.add(Long.parseLong(s)));
                return styles;
            } catch (NumberFormatException e) {
                throw new BadRequestException("A URI da requisição contém 1 ou mais atributos inválidos! Por favor, " +
                        "verifique erro de sintaxe e tente novamente.");
            }
        }

        return null;
    }

}
