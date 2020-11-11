package com.leoncarraro.breweryapi.model;

import com.leoncarraro.breweryapi.dto.StyleRequest;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_style")
@Getter
@Setter
@EqualsAndHashCode
public class Style {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "style")
    private List<Beer> beers = new ArrayList<>();

    public Style() {
    }

    public Style(StyleRequest styleRequest) {
        id = null;
        name = styleRequest.getName();
    }

}
