package com.br.cursospring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.cursospring.domain.Pedido;
import com.br.cursospring.repositories.PedidoRepository;
import com.br.cursospring.services.exceptions.ObjectNotFoundException;


@Service
public class PedidoService {
    @Autowired
	private PedidoRepository repo;
	
	public Pedido find(Integer id) {
		Pedido obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: "+ id +", Tipo: "+ Pedido.class.getName());
		}
		return obj;
	
	}

}
