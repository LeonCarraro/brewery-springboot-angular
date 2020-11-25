package com.leoncarraro.breweryapi.model;

import com.leoncarraro.breweryapi.model.enums.ClientType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tb_client")
@Getter
@Setter
@EqualsAndHashCode
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Enumerated(value = EnumType.STRING)
    private ClientType clientType;
    private String document;
    private String phoneNumber;
    private String email;

    @Embedded
    private Address address;

}
