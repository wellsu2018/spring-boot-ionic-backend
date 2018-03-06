package com.br.cursospring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.cursospring.domain.Categoria;
import com.br.cursospring.repositories.CategoriaRepository;


@Service
public class CategoriaService {
    @Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		
		Categoria obj = repo.findOne(id);
		return obj;
	
	}

}
