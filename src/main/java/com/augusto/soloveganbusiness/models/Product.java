package com.augusto.soloveganbusiness.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.JoinColumn;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@NoArgsConstructor
@Getter
@Setter
public class Product extends BaseModel {

    @NotEmpty(message = "Este campo no puede estar en blanco.")
    @Size(min = 3, max = 128, message = "Nombre debe tener entre 3 y 128 caracteres")
    private String name;

    @NotEmpty(message = "Este campo no puede estar en blanco.")
    @Size(min = 5, max = 1000, message = "Descripción no puede tener menos de 5 caracteres")
    private String description;

    @NotNull
    private String imgUrl;
    private String imgRoute;

    private Long portionValue;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "products_has_ingredients", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<Ingredient> ingredients;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Quantity> quantities = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "products_has_certificates", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "certificate_id"))
    private List<Certificate> certificates;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "prices", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "store_id"))
    private List<Store> stores;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "favorite_products", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;
}
