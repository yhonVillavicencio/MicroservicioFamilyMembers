package com.microservicio.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.microservicio.model.FamilyMembers;

public interface FamilyMembersRepository extends ReactiveMongoRepository<FamilyMembers, String>{

}
