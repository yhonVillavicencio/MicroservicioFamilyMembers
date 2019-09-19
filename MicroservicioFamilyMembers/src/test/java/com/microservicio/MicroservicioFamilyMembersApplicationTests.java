package com.microservicio;

import java.util.Collections;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.microservicio.model.FamilyMembers;
import com.microservicio.repository.FamilyMembersRepository;

import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MicroservicioFamilyMembersApplicationTests {

	 @Autowired
	  private WebTestClient client;
	  @Autowired
	  private FamilyMembersRepository servi;

	  @Test
	  public void listar() {
	    client.get()
	         .uri("/api/v2/st")
	         .accept(MediaType.APPLICATION_JSON_UTF8)
	         .exchange()
	         .expectStatus().isOk()
	         .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
	         .expectBodyList(FamilyMembers.class)
	         .consumeWith(response -> {
	           List<FamilyMembers> family = response.getResponseBody();
	           family.forEach(s -> {
	             System.out.println(s.getNombre() + " - " + s.getCodigoStudent());
	           });
	           Assertions.assertThat(family.size() > 0).isTrue();
	         });
	  }

	  @Test
	  public void BuscarPorNumeroIdentificacion() {
	    FamilyMembers fm = servi.findBynumeroIdentificacion("").block();
	    client.get()
	      .uri("/api/v2/st/{id}", Collections.singletonMap("id",fm.getCodigoStudent()))
	      .accept(MediaType.APPLICATION_JSON_UTF8)
	      .exchange()
	      .expectStatus().isOk()
	      .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
	      .expectBody()
	      .jsonPath("$.codigoStudent").isNotEmpty()
	      .jsonPath("$.numeroIdentificacion").isEqualTo("");
	  }

	  @Test
	  public void Crear() {
	    FamilyMembers fm = new FamilyMembers("DNI","12345611","prueba","0005","Masculino","padre");
	    client.post()
	      .uri("/api/v2/st")
	      .contentType(MediaType.APPLICATION_JSON_UTF8)
	      .accept(MediaType.APPLICATION_JSON_UTF8)
	      .body(Mono.just(fm), FamilyMembers.class)
	      .exchange()
	      .expectStatus().isOk()
	      .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
	      .expectBody()
	      .jsonPath("$.codigoStudent").isNotEmpty()
	      .jsonPath("$.nombre").isEqualTo("prueba");
	  } 

	  @Test
	  public void editarTest() {
	    FamilyMembers fm = servi.findBynumeroIdentificacion("73226949").block();

	    FamilyMembers fmEd = new FamilyMembers("DNI","12345611","prueba","0005","Masculino","padre");
	    client.put()
	      .uri("/api/v2/st/{id}", Collections.singletonMap("id", fm.getCodigoStudent()))
	      .contentType(MediaType.APPLICATION_JSON_UTF8)
	      .accept(MediaType.APPLICATION_JSON_UTF8)
	      .body(Mono.just(fmEd), FamilyMembers.class)
	      .exchange()
	      .expectStatus().isOk()
	      .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
	      .expectBody()
	      .jsonPath("$.codigoStudent").isNotEmpty()
	      .jsonPath("$.nombre").isEqualTo("prueba");
	  }


	  @Test
	  public void eliminarTest() {
	    FamilyMembers fm = servi.findBynumeroIdentificacion("12345678").block();
	    client.delete()
	      .uri("/api/v2/st/{id}", Collections.singletonMap("id", fm.getCodigoStudent()))
	      .exchange()
	      .expectStatus().isOk()
	      .expectBody()
	      .isEmpty();
	  } 


}
