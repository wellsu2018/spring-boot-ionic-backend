package com.br.cursospring;

import java.text.SimpleDateFormat;
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
import com.br.cursospring.domain.ItensPedido;
import com.br.cursospring.domain.Pagamento;
import com.br.cursospring.domain.PagamentoComBoleto;
import com.br.cursospring.domain.PagamentoComCartao;
import com.br.cursospring.domain.Pedido;
import com.br.cursospring.domain.Produto;
import com.br.cursospring.domain.enums.EstadoPagamento;
import com.br.cursospring.domain.enums.TipoCliente;
import com.br.cursospring.repositories.CategoriaRepository;
import com.br.cursospring.repositories.CidadeRepository;
import com.br.cursospring.repositories.ClienteRepository;
import com.br.cursospring.repositories.EnderecoRepository;
import com.br.cursospring.repositories.EstadoRepository;
import com.br.cursospring.repositories.ItensPedidoRepository;
import com.br.cursospring.repositories.PagamentoRepository;
import com.br.cursospring.repositories.PedidoRepository;
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

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItensPedidoRepository itensPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursospringApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório");
		Categoria cat3 = new Categoria(null,"Cama Mesa e Banho");
		Categoria cat4 = new Categoria(null,"Eletrônicos");
		Categoria cat5 = new Categoria(null,"Jardinagem");
		Categoria cat6 = new Categoria(null,"Decoração");
		Categoria cat7 = new Categoria(null,"Perfumaria");
		
		Produto p1 = new Produto(null,"Computador",2000.00);
		Produto p2 = new Produto(null,"Impressora",800.00);
		Produto p3 = new Produto(null,"Mouse",80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.save(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7));
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
		Cliente cli2 = new Cliente(null, "Maria Silva","maria@gmail.com","36378912377",TipoCliente.PESSOAFISICA);
		
	    cli1.getTelefones().addAll(Arrays.asList("81998158460","8134344214"));
	    cli2.getTelefones().addAll(Arrays.asList("37363323","93838393"));
	    
	    Endereco e1 = new Endereco(null, "Rua Brejo da Madre de Deus", "300", "Bl. A14 Apto 104 ", "Janga", "53437040", cli1, c4);
	    Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli2, c1);
	    Endereco e3 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli2, c2);
	    
	    cli1.getEnderecos().addAll(Arrays.asList(e1));
	    cli2.getEnderecos().addAll(Arrays.asList(e2,e3));
	    
	    clienteRepository.save(Arrays.asList(cli1));
	    clienteRepository.save(Arrays.asList(cli2));
	    enderecoRepository.save(Arrays.asList(e1,e2,e3));
	    
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	    
	    Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
	    Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli2, e2);
	    
	    Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
	    ped1.setPagamento(pagto1);
	    
	    Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"),null);
	    ped2.setPagamento(pagto2);
	    
	    cli1.getPedidos().addAll(Arrays.asList(ped1));
	    cli2.getPedidos().addAll(Arrays.asList(ped2));
	    
	    pedidoRepository.save(Arrays.asList(ped1,ped2));
	    pagamentoRepository.save(Arrays.asList(pagto1,pagto2));
	    
	    ItensPedido ip1 = new ItensPedido(ped1, p1, 0.00, 1, 2000.00);
	    ItensPedido ip2 = new ItensPedido(ped1, p2, 0.00, 2, 80.00); 
	    ItensPedido ip3 = new ItensPedido(ped2, p3, 100.00, 1, 800.00);
	    
	    ped1.getItens().addAll(Arrays.asList(ip1,ip2));
	    ped2.getItens().addAll(Arrays.asList(ip3));
	    
	    p1.getItens().addAll(Arrays.asList(ip1));
	    p2.getItens().addAll(Arrays.asList(ip2));
	    p3.getItens().addAll(Arrays.asList(ip3));
	    
	    itensPedidoRepository.save(Arrays.asList(ip1,ip2,ip3));
	    
	    
	    
	    
	    
	    
	    
	}
}
