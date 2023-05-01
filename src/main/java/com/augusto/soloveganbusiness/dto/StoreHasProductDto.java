package com.augusto.soloveganbusiness.dto;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.URL;

import com.augusto.soloveganbusiness.models.Product;
import com.augusto.soloveganbusiness.models.Store;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class StoreHasProductDto extends BaseDto {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @NotNull
    @Positive
    private int normalPrice;

    @Positive
    private int offerPrice;

    @NotEmpty(message = "Este campo no puede estar vacío.")
    @URL(message = "Por favor ingrese una URL válida")
    private String imgRouteLinks;
}
