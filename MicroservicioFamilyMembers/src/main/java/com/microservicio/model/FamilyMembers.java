package com.microservicio.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "FamilyMembers")
public class FamilyMembers {
  @Id
  private String codigoDelFamiliar;
  @NotEmpty(message = "Tipo de Identificacion No puede estar vacio")
  private String tipoIdentificacion;
  private String numeroIdentificacion;
  @NotEmpty(message = "Nombre No puede estar vacio")
  private String nombre;
  private String codigoStudent;
  @NotEmpty(message = "Genero No puede estar vacio")
  private String genero;
  @NotEmpty(message = "Tipo del Familiar No puede estar vacio")
  private String tipoFamiliar;
}
