package com.leoncarraro.breweryapi.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CityRequest {

    @NotBlank(message = "O campo Nome é de preenchimento obrigatório!")
    @Length(min = 5, max = 50, message = "O campo Nome deve conter entre 5 e 50 caracteres!")
    private String name;

    @NotNull(message = "O campo Estado é de preenchimento obrigatorio!")
    private Long stateId;

}
