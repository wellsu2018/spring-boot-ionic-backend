package com.br.cursospring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.cursospring.domain.Categoria;
import com.br.cursospring.repositories.CategoriaRepository;
import com.br.cursospring.services.exceptions.ObjectNotFoundException;


@Service
public class CategoriaService {
    @Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		Categoria obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: "+ id +", Tipo: "+ Categoria.class.getName());
		}
		return obj;
	
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

}
