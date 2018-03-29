package com.br.cursospring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.cursospring.domain.Estado;
import com.br.cursospring.repositories.EstadoRepository;
import com.br.cursospring.services.exceptions.ObjectNotFoundException;


@Service
public class EstadoService {
    @Autowired
	private EstadoRepository repo;
	
	public Estado find(Integer id) {
		Estado obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: "+ id +", Tipo: "+ Estado.class.getName());
		}
		return obj;
	
	}

}
