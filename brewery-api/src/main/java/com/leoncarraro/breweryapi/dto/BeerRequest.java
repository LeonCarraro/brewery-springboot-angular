package com.leoncarraro.breweryapi.dto;

import com.leoncarraro.breweryapi.model.validation.SKU;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
public class BeerRequest {

    @NotNull(message = "O campo SKU é de preenchimento obrigatorio!")
    @SKU
    private String sku;

    @NotBlank(message = "O campo Nome é de preenchimento obrigatorio!")
    @Length(min = 5, max = 50, message = "O campo Nome deve conter entre 5 e 50 caracteres!")
    private String name;

    @Length(max = 255, message = "O campo Descrição deve conter até 255 caracteres!")
    private String description;

    @NotNull(message = "O campo Volume é de preenchimento obrigatorio!")
    @Min(value = 1, message = "O Volume mínimo é 1ml")
    @Max(value = 65535, message = "O Volume máximo é 65535ml")
    private Integer volume;

    @NotNull(message = "O campo Valor é de preenchimento obrigatorio!")
    @DecimalMin(value = "0", message = "O Valor mínimo é R$ 0,00")
    @DecimalMax(value = "999999999.99", message = "O Valor máximo é R$ 999.999.999,99")
    private BigDecimal value;

    @NotNull(message = "O campo Conteúdo Alcoólico é de preenchimento obrigatorio!")
    @DecimalMin(value = "0", message = "O Conteúdo Alcoólico mínimo é 0%")
    @DecimalMax(value = "100", message = "O Conteúdo Alcoólico máximo é 100%")
    private BigDecimal alcoholContent;

    @NotNull(message = "O campo Comissão é de preenchimento obrigatorio!")
    @DecimalMin(value = "0", message = "A Comissão mínima é 0%")
    @DecimalMax(value = "100", message = "A Comissão máxima é 100%")
    private BigDecimal comission;

    @NotNull(message = "O campo Quantidade em Estoque é de preenchimento obrigatorio!")
    @Min(value = 0, message = "A Quantidade em Estoque mínima é 0 unidades!")
    @Max(value = 2147483647, message = "A Quantidade em Estoque máxima é 2.147.483.647 unidades!")
    private Integer stockQuantity;

    @NotBlank(message = "O campo Origem é de preenchimento obrigatorio!")
    private String origin;

    @NotBlank(message = "O campo Sabor é de preenchimento obrigatorio!")
    private String flavor;

    @NotNull(message = "O campo Estilo é de preenchimento obrigatorio!")
    private Long styleId;

}
