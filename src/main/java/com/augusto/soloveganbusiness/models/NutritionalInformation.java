package com.augusto.soloveganbusiness.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "nutritionalInformation")
@NoArgsConstructor
@Getter
@Setter
public class NutritionalInformation extends BaseModel {

    @NotEmpty(message = "Este campo no puede estar en blanco.")
    private String description;

    @OneToMany(mappedBy = "nutritionalInformation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Quantity> quantities = new ArrayList<>();
}
