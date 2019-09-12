package com.microservicio.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.microservicio.model.FamilyMembers;
import com.microservicio.repository.FamilyMembersRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FamilyMembersServiceImpl implements FamilyMembersService{

	@Autowired
	private FamilyMembersRepository resp; // se inyecta el repositorio
	
	
	@Override
	public Flux<FamilyMembers> findAll() {
		// TODO Auto-generated method stub
		return resp.findAll();
	}

	@Override
	public Mono<FamilyMembers> save(FamilyMembers familyMembersService) {
		// TODO Auto-generated method stub
		return resp.save(familyMembersService);
	}

	@Override
	public Mono<FamilyMembers> findById(String id) {
		// TODO Auto-generated method stub
		return resp.findById(id);
	}

	@Override
	public Mono<Void> delete(FamilyMembers familyMembers) {
		// TODO Auto-generated method stub
		return resp.delete(familyMembers);
	}


}
