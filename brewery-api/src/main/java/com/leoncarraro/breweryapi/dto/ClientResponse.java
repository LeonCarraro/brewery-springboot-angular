package com.leoncarraro.breweryapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientResponse {

    private Long id;
    private String name;
    private String clientType;
    private String document;
    private String phoneNumber;
    private String email;
    private String cep;
    private String number;
    private String complement;
    private Long cityId;
    private Long stateId;

}
