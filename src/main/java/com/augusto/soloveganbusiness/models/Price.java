package com.augusto.soloveganbusiness.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "prices")
@NoArgsConstructor
@Getter
@Setter
public class Price extends BaseModel {

    @NotNull
    @Positive
    private Long normalPrice;

    @Positive
    private Long offerPrice;

    // @NotEmpty(message = "Este campo no puede estar en blanco.")
    // @URL(message = "Por favor ingrese una URL válida")
    // private String imgRouteLinks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}
