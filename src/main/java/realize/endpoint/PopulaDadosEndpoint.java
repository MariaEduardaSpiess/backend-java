package realize.endpoint;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import realize.domain.impl.AreaInteresseImpl;
import realize.domain.impl.EntidadeImpl;
import realize.domain.impl.EventoImpl;
import realize.domain.impl.ParticipanteEventoImpl;
import realize.domain.impl.SegmentoImpl;
import realize.domain.impl.VoluntarioImpl;
import realize.endpoint.json.ConfigEmailParam;
import realize.mail.ConfigEmailImpl;
import realize.repo.AreaInteresseRepository;
import realize.repo.EntidadeRepository;
import realize.repo.EventoRepository;
import realize.repo.ParticipanteEventoRepository;
import realize.repo.SegmentoRepository;
import realize.repo.VoluntarioRepository;

@RestController
@RequestMapping( "/api/public/v1/popula" )
public class PopulaDadosEndpoint {
	
	@Autowired
	private ConfigEmailImpl cfgmail;

	@Autowired
	private SegmentoRepository segrepo;
	
	@Autowired
	private EntidadeRepository entrepo;

	@Autowired
	private VoluntarioRepository volrepo;
	
	@Autowired
	private AreaInteresseRepository arearepo;
	
	@Autowired
	private EventoRepository eventorepo;

	@Autowired
	private ParticipanteEventoRepository partrepo;
	
	@PutMapping
	public void popula() {
		
		configuraEmail();
		
		// Segmentos
		addSegmento("Meio Ambiente");
		addSegmento("Idosos");
		addSegmento("Orfanatos");
		addSegmento("Jovens");
		addSegmento("Saúde");
		addSegmento("Apoio Emocional");
		addSegmento("Adultos");
		addSegmento("Crianças");
		addSegmento("Animais");
		
		
		// Voluntario
		VoluntarioImpl vol1 = addVoluntario("47 99914-9472", "990.086.706-82", "andre@cbsistemas.com.br", "89031620", "Blumenau", "casa 5", "SC", "Rua Doutor Fritz Mueller", 570, "Salto", "Brasil", "André Ricardo Spiess", "47 3209-9472", "1234");
		addAreasInteresse(vol1, 1,3);

		VoluntarioImpl vol2 = addVoluntario("47 98836-9472", "995.286.716-82", "tainara@gmail.com", "89031618", "Gaspar", "apto 101", "SC", "Rua Doralicio Garcia", 236, "Centro", "Brasil", "Tainara Freitas", "47 3335-9472", "1234");
		addAreasInteresse(vol2, 1,2);

		VoluntarioImpl vol3 = addVoluntario("47 99778-3322", "885.446.755-82", "arthurrspiess@gmail.com", "89031618", "Pomerode", "apto 303", "SC", "Rua Blumenau", 236, "Testo Salto", "Brasil", "Arthur Ricardo Spiess", "47 3378-9599", "1234");
		addAreasInteresse(vol3, 2,3);
		
		// Entidade
		EntidadeImpl ent1 = addEntidade("1", "82656554000106", "contato@apae.com.br", "89035600", "Blumenau", "", "SC", "Rua Casemiro de Abreu", 216, "Vila Nova", "Brasil", "APAE", "47 3323-0000", "1234");
		EntidadeImpl ent2 = addEntidade("2", "61956496000166", "contato@cvv.com.br", "89036070", "Blumenau", "", "SC", "Rua Professor Luiz Schwartz", 169, "Velha", "Brasil", "CVV", "47 3329-4111", "1234");
		EntidadeImpl ent3 = addEntidade("3", "03585420000175", "contato@aprablu.com.br", "89036070", "Blumenau", "", "SC", "Rua Germano Kratz Neto", 158, "Velha Grande", "Brasil", "Lar Bethel", "47 3330-1327", "1234");
		EntidadeImpl ent4 = addEntidade("4", "03585420000175", "contato@ablucan.com.br", "89036070", "Blumenau", "", "SC", "Rua 25 de Julho", 439, "Itoupava Norte", "Brasil", "ABLUCAN", "47 3035-4800", "1234");
		
		// Eventos
		Calendar cal = Calendar.getInstance();
		cal.set(2018,9,10);
		Date dataold = cal.getTime();
		Date data = new Date();
		
		EventoImpl evento1 = addEvento(dataold,"A APAE Blumenau irá realizar um pedágio para arrecadação de recursos para investimento.", "Garcia", "89031001", "Blumenau", "", "SC", "Rua Amazonas", 201, "Brasil", "09:00", true, "Pedágio APAE BLumenau 2017", 20, ent1, 1);
		addParticipanteEvento(evento1,vol1);
		addParticipanteEvento(evento1,vol2);
		
		EventoImpl evento2 = addEvento(data,"Caminhada aberta ao público em prol do setembro amarelo, mês de prevenção ao suicídio.", "Escola Agricola", "89031101", "Blumenau", "", "SC", "Rua Benjamin Constant", 124, "Brasil", "16:00", true, "Caminhada pela Valorização da Vida", 15, ent2, 2);
		addParticipanteEvento(evento2,vol2);
		addParticipanteEvento(evento2,vol3);

		EventoImpl evento3 = addEvento(data, "O Lar Berthel realizará uma festa junina para as crianças. O objetivo é que elas tenham uma tarde agradável e criem boas lembranças.", "Estrada das Areias", "89031101", "Indaial", "", "SC", "Rua José Domingos de Oliveira", 231, "Brasil", "11:00", false, "Festa Junina", 3, ent3, 3);
		addParticipanteEvento(evento3,vol1);
		addParticipanteEvento(evento3,vol3);

		EventoImpl evento4 = addEvento(data,"Os palhaços vão invadir o hospital!. Neste dia em especial, voluntários vestidos de palhaço poderão entrar em contato com pacientes para animá-las.", "Centro", "89031101", "Itajai", "", "SC", "Rua Felipe Schmith", 132, "Brasil", "13:00", false, "Palhaçada no hospital", 5, ent4, 3);
		addParticipanteEvento(evento4,vol1);
		addParticipanteEvento(evento4,vol3);
		
	}

	private void addParticipanteEvento(EventoImpl evento, VoluntarioImpl vol) {
		ParticipanteEventoImpl part = new ParticipanteEventoImpl();
		part.setAceito(!evento.isSelecao());
		part.setEvento(evento);
		part.setVoluntario(vol);
		
		partrepo.save(part);
	}

	private EventoImpl addEvento(Date data,String descricao, String bairro, String cep, String cidade, String complemento, String estado,
			String endereco, int numero, String pais, String hora, boolean selecao, String titulo, int vagas, EntidadeImpl ent, int segId) {
		
		SegmentoImpl segmento = segrepo.findOne(new Long(segId));
		
		EventoImpl evento = new EventoImpl();
		evento.setData(data);
		evento.setDescricao(descricao);
		evento.setEndBairro(bairro);
		evento.setEndCep(cep);
		evento.setEndCidade(cidade);
		evento.setEndComplemento(complemento);
		evento.setEndEstado(estado);
		evento.setEndLogradouro(endereco);
		evento.setEndNumero(numero);
		evento.setEndPais(pais);
		evento.setHora(hora);
		evento.setSelecao(selecao);
		evento.setTitulo(titulo);
		evento.setVagas(vagas);
		evento.setEntidade(ent);
		evento.setSegmento(segmento);
		
		eventorepo.save(evento);
		
		return evento;
		
	}

	private void addAreasInteresse(VoluntarioImpl vol, Integer ...idSegs) {
		for (Integer id : idSegs) {
			
			SegmentoImpl seg = segrepo.findOne(new Long(id));
			
			AreaInteresseImpl area = new AreaInteresseImpl();
			area.setVoluntario(vol);
			area.setSegmento(seg);
			
			arearepo.save(area);
		}	
	}

	private void configuraEmail() {
		ConfigEmailParam cfg = new ConfigEmailParam();
		cfg.setHostName("smtp.gmail.com");
		cfg.setMailAdm("andre@cbsistemas.com.br");
		cfg.setMailFrom("andrerspiess@gmail.com");
		cfg.setPassword("mfc413304.");
		cfg.setSmtpPort(465);
		cfg.setSsl(true);
		cfg.setTls(false);
		cfg.setUserName("andrerspiess@gmail.com");
		cfg.setCharSet("utf-8");
		cfgmail.save(cfg);
	}

	private EntidadeImpl addEntidade(String celular, String cnpj, String email, String cep, String cidade, String complemento,
			String estado, String endereco, Integer numero, String bairro, String pais, String nome, String telefone, String senha) {
		
		EntidadeImpl ent = new EntidadeImpl();
		
		ent.setCelular(celular);
		ent.setDocumento(cnpj);
		ent.setEmail(email);
		ent.setEndCep(cep);
		ent.setEndCidade(cidade);
		ent.setEndComplemento(complemento);
		ent.setEndestado(estado);
		ent.setEndLogradouro(endereco);
		ent.setEndNumero(numero);
		ent.setEndBairro(bairro);
		ent.setEndPais(pais);
		ent.setNomeCompleto(nome);
		ent.setTelefone(telefone);
		ent.setUserPassword(senha);
		
		entrepo.save(ent);
		return ent;
		
	}

	private VoluntarioImpl addVoluntario(String celular, String cnpj, String email, String cep, String cidade, String complemento,
			String estado, String endereco, Integer numero, String bairro, String pais, String nome, String telefone, String senha) {
		
		VoluntarioImpl ent = new VoluntarioImpl();
		
		ent.setCelular(celular);
		ent.setDocumento(cnpj);
		ent.setEmail(email);
		ent.setEndCep(cep);
		ent.setEndCidade(cidade);
		ent.setEndComplemento(complemento);
		ent.setEndestado(estado);
		ent.setEndLogradouro(endereco);
		ent.setEndNumero(numero);
		ent.setEndBairro(bairro);
		ent.setEndPais(pais);
		ent.setNomeCompleto(nome);
		ent.setTelefone(telefone);
		ent.setUserPassword(senha);
		
		volrepo.save(ent);
		return ent;
	}

	
	private void addSegmento(String nome) {
		SegmentoImpl seg = new SegmentoImpl();
		seg.setNome(nome);
		segrepo.save(seg);
	}
	
	

}
