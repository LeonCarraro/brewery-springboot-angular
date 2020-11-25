package com.leoncarraro.breweryapi.dto;

import com.leoncarraro.breweryapi.model.validation.group.CNPJGroup;
import com.leoncarraro.breweryapi.model.validation.group.CPFGroup;
import com.leoncarraro.breweryapi.model.validation.group.ClientGroupSequenceProvider;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@GroupSequenceProvider(value = ClientGroupSequenceProvider.class)
public class ClientRequest {

    @NotBlank(message = "O campo Nome é de preenchimento obrigatorio!")
    @Length(min = 5, max = 80, message = "O campo Nome deve conter entre 5 e 80 caracteres!")
    private String name;

    @NotBlank(message = "O campo Tipo de Cliente é de preenchimento obrigatorio!")
    private String clientType;

    @NotBlank(message = "O campo CPF/CNPJ é de preenchimento obrigatorio!")
    @CPF(groups = CPFGroup.class, message = "CPF inválido!")
    @CNPJ(groups = CNPJGroup.class, message = "CNPJ inválido!")
    private String document;

    @Length(min = 10, max = 11, message = "O campo Número de Telefone deve conter entre 10 e 11 caracteres!")
    private String phoneNumber;

    @NotBlank(message = "O campo Email é de preenchimento obrigatorio!")
    @Length(max = 80, message = "O campo Email deve conter até 80 caracteres!")
    @Email(message = "Email inválido!")
    private String email;

    @NotBlank(message = "O campo CEP é de preenchimento obrigatorio!")
    @Length(min = 5, max = 10, message = "O campo CEP deve conter entre 5 e 10 caracteres!")
    private String cep;

    @NotBlank(message = "O campo Número é de preenchimento obrigatorio!")
    @Length(max = 10, message = "O campo Número deve conter até 10 caracteres!")
    private String number;

    @Length(max = 20, message = "O campo Complemento deve conter até 20 caracteres!")
    private String complement;

    @NotNull(message = "O campo Cidade é de preenchimento obrigatorio!")
    private Long cityId;

}
