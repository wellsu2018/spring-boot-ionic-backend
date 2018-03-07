package com.br.cursospring;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.br.cursospring.domain.Categoria;
import com.br.cursospring.domain.Cidade;
import com.br.cursospring.domain.Cliente;
import com.br.cursospring.domain.Endereco;
import com.br.cursospring.domain.Estado;
import com.br.cursospring.domain.Produto;
import com.br.cursospring.domain.enums.TipoCliente;
import com.br.cursospring.repositories.CategoriaRepository;
import com.br.cursospring.repositories.CidadeRepository;
import com.br.cursospring.repositories.ClienteRepository;
import com.br.cursospring.repositories.EnderecoRepository;
import com.br.cursospring.repositories.EstadoRepository;
import com.br.cursospring.repositories.ProdutoRepository;

@SpringBootApplication
public class CursospringApplication implements CommandLineRunner{
    
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursospringApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório");
		
		Produto p1 = new Produto(null,"Computador",2000.00);
		Produto p2 = new Produto(null,"Impressora",800.00);
		Produto p3 = new Produto(null,"Mouse",80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.save(Arrays.asList(cat1,cat2));
		produtoRepository.save(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		Estado est3 = new Estado(null, "Pernambuco");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		Cidade c4 = new Cidade(null, "Paulista", est3);
		Cidade c5 = new Cidade(null, "Recife", est3);
		Cidade c6 = new Cidade(null, "Olinda", est3);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		est3.getCidades().addAll(Arrays.asList(c4,c5,c6));
		
		estadoRepository.save(Arrays.asList(est1,est2,est3));
		cidadeRepository.save(Arrays.asList(c1,c2,c3,c4,c5,c6));
		
		Cliente cli1 = new Cliente(null, "Wellington Alves de Oliveira","wellsu@gmail.com","89889614472",TipoCliente.PESSOAFISICA);
		
	    cli1.getTelefones().addAll(Arrays.asList("81998158460","8134344214"));
	    
	    Endereco e1 = new Endereco(null, "Rua Brejo da Madre de Deus", "300", "Bl. A14 Apto 104 ", "Janga", "53437040", c4, cli1);
	    Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", c2, cli1);
	    
	    cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
	    
	    clienteRepository.save(Arrays.asList(cli1));
	    enderecoRepository.save(Arrays.asList(e1,e2));
	}
}
