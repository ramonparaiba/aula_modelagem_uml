package com.pabloramon.aulauml.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pabloramon.aulauml.domain.Categoria;
import com.pabloramon.aulauml.repositories.CategoriaRepository;
import com.pabloramon.aulauml.services.exceptions.ObjectoNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id){
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectoNotFoundException(
				"Objeto não encontrado! ID: "+id + ", Tipo: " + Categoria.class.getName()));
	}
}
