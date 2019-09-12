package com.microservicio.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "FamilyMembers")
public class FamilyMembers {
	
	@Id
	private String codigoDelFamiliar;
	private String tipoIdentificacion;
	private String numeroIdentificacion;
	private String nombre;
	private String codigoStudent;
	private String genero;
	private String tipoFamiliar;
	

}
