package com.augusto.soloveganbusiness.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "certificates")
@NoArgsConstructor
@Getter
@Setter
public class Certificate extends BaseModel {
    @NotEmpty(message = "Este campo no puede estar en blanco.")
    private String typeCertificate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_preferences", joinColumns = @JoinColumn(name = "certificate_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "products_has_certificates", joinColumns = @JoinColumn(name = "certificate_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;
}