package com.microservicio.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.microservicio.model.FamilyMembers;
import com.microservicio.repository.FamilyMembersRepository;

@RestController
@RequestMapping("/api/familyMembers")//ruta principal
public class FamilyMembersController {
	
	@Autowired
	private FamilyMembersRepository servicio;
	
	@GetMapping
	public Flux<FamilyMembers> listar(){
		return servicio.findAll();
	}
	
	@PostMapping
	public Mono<FamilyMembers> crear(@RequestBody FamilyMembers familyMembers){
	
		return servicio.save(familyMembers);
	}
	
	@GetMapping("/{id}")
	public Mono<FamilyMembers> ver(@PathVariable String id){
	return servicio.findById(id);
	}

	@PutMapping("/{id}")
	public Mono<FamilyMembers>editar(@RequestBody FamilyMembers familyMembers, @PathVariable String id){
		return servicio.findById(id).flatMap(s -> {
			s.setTipoIdentificacion(familyMembers.getTipoIdentificacion());
			s.setNumeroIdentificacion(familyMembers.getNumeroIdentificacion());
			s.setNombre(familyMembers.getNombre());
			s.setGenero(familyMembers.getGenero());
			s.setCodigoStudent(familyMembers.getCodigoStudent());
			s.setTipoFamiliar(familyMembers.getTipoFamiliar());
			
			return servicio.save(s);
		});
		
	}
	
	@DeleteMapping("/{id}")
	public Mono<Void> eliminar(@PathVariable String id){
		return servicio.findById(id).flatMap(s ->{
			return servicio.delete(s);
	});
	
	}
	

}
