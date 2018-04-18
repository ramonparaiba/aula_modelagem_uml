package com.pabloramon.aulauml.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pabloramon.aulauml.domain.Cliente;
import com.pabloramon.aulauml.repositories.ClienteRepository;
import com.pabloramon.aulauml.services.exceptions.ObjectoNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id){
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectoNotFoundException(
				"Objeto não encontrado! ID: "+id + ", Tipo: " + Cliente.class.getName()));
	}
}
