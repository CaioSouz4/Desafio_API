package com.desafio.desafio.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.desafio.model.User;
import com.desafio.desafio.repository.UserRepository;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository repository;

	
	@GetMapping("/users") 
	public @ResponseBody Iterable<User> list() {
		Iterable<User> list = repository.findAll();
		return list;		
	}	
	
	@PostMapping("/cadastrar")
	public User cadastrar(@RequestBody @Valid User user) {
		return repository.save(user);
	}	

	@DeleteMapping("/users/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
	
	@GetMapping("/users/{id}")
	public Optional<User> find(@PathVariable(value = "id") Long id) {
	    return repository.findById(id);	            
	}	

	@PutMapping("/users/{id}")
	public User addHoras(@PathVariable(value = "id") Long id, @Valid @RequestBody User user) {
	    User novo = repository.findById(id).get(); 
	    novo.setHours(user.getHours() + novo.getHours());
	    novo.getRegistro().add(user.getHours());
	    repository.save(novo);
	    
	    return novo;
	}
	
	@GetMapping("/registro/{id}")
	public List<Integer> registro(@PathVariable(value = "id") Long id) {
		User user = repository.findById(id).get();
		return user.getRegistro();	
	}	
	
	
	@PutMapping("/registro/{id}")
	public User registrarHora(@PathVariable(value = "id") Long id, @Valid @RequestBody User user) {		
		    User novo = repository.findById(id).get(); 
		    //novo.setHours(user.getHours() + novo.getHours());
		    //novo.getRegistro().add(user.getHours());
		    novo.getRegistro().addAll(user.getRegistro());
		    
		    if(!user.getRegistro().isEmpty()) {
		    	int auxiliar = 0;
		    	for(int aux : user.getRegistro()) {
		    		auxiliar = aux + auxiliar;
		    		
		    	}
		    	novo.setHours(auxiliar + novo.getHours());
		    }
		    
		    User atualizado = repository.save(novo);
		    return atualizado;
	}	
	
	
}
