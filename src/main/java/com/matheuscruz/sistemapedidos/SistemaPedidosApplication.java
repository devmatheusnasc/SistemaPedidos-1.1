package com.matheuscruz.sistemapedidos;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.matheuscruz.sistemapedidos.domain.Categoria;
import com.matheuscruz.sistemapedidos.domain.Cidade;
import com.matheuscruz.sistemapedidos.domain.Cliente;
import com.matheuscruz.sistemapedidos.domain.Endereco;
import com.matheuscruz.sistemapedidos.domain.Estado;
import com.matheuscruz.sistemapedidos.domain.ItemPedido;
import com.matheuscruz.sistemapedidos.domain.Pagamento;
import com.matheuscruz.sistemapedidos.domain.PagamentoComBoleto;
import com.matheuscruz.sistemapedidos.domain.PagamentoComCartao;
import com.matheuscruz.sistemapedidos.domain.Pedido;
import com.matheuscruz.sistemapedidos.domain.Produto;
import com.matheuscruz.sistemapedidos.domain.enums.EstadoPagamento;
import com.matheuscruz.sistemapedidos.domain.enums.TipoCliente;
import com.matheuscruz.sistemapedidos.repository.CategoriaRepository;
import com.matheuscruz.sistemapedidos.repository.CidadeRepository;
import com.matheuscruz.sistemapedidos.repository.ClienteRepository;
import com.matheuscruz.sistemapedidos.repository.EnderecoRepository;
import com.matheuscruz.sistemapedidos.repository.EstadoRepository;
import com.matheuscruz.sistemapedidos.repository.ItemPedidoRepository;
import com.matheuscruz.sistemapedidos.repository.PagamentoRepository;
import com.matheuscruz.sistemapedidos.repository.PedidoRepository;
import com.matheuscruz.sistemapedidos.repository.ProdutoRepository;

@SpringBootApplication
public class SistemaPedidosApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SistemaPedidosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		if (categoriaRepository.findAll().size() == 0) {
			categoriaRepository.saveAll(Arrays.asList(cat1, cat2));	
		}
		if (produtoRepository.findAll().size() == 0) {
			produtoRepository.saveAll(Arrays.asList(p1, p2, p3));	
		}
		
		
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campias", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		if (estadoRepository.findAll().size() == 0) {
			estadoRepository.saveAll(Arrays.asList(est1, est2));
		}
		if (cidadeRepository.findAll().size() == 0) {
			cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));	
		}
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "Maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "100", "Sala 800", "Centro", "38777012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		if (clienteRepository.findAll().size() == 0) {
			clienteRepository.saveAll(Arrays.asList(cli1));
		}
		if (enderecoRepository.findAll().size() == 0) {
			enderecoRepository.saveAll(Arrays.asList(e1, e2));
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null,sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pgto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pgto1);
		
		Pagamento pgto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pgto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		if (pedidoRepository.findAll().size() == 0) {
			pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		}
		if (pagamentoRepository.findAll().size() == 0) {
			pagamentoRepository.saveAll(Arrays.asList(pgto1, pgto2));
		}
		
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 00.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p2.getItens().addAll(Arrays.asList(ip2));
		
		if(itemPedidoRepository.findAll().size() == 0) {
			itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));	
		}
		
	}

}
