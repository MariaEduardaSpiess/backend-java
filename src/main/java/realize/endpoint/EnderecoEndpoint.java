package realize.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import realize.domain.Estado;
import realize.domain.Pais;
import realize.domain.impl.CidadeImpl;
import realize.domain.impl.EstadoImpl;
import realize.domain.impl.PaisImpl;
import realize.exeptions.ErroNoProcessoException;
import realize.repo.CidadeRepository;
import realize.repo.EstadoRepository;
import realize.repo.PaisRepository;

@RestController
@RequestMapping( "/api/public/v1/endereco" )
public class EnderecoEndpoint {

	@Autowired
	private PaisRepository repopais;
	
	@Autowired
	private EstadoRepository repoestado;
	
	@Autowired
	private CidadeRepository repocidade;
	
	
	@GetMapping("listPaises")
	public List<PaisImpl> getListaPaises() {
		return repopais.findAll(); 
	}

	@GetMapping("listEstados")
	public List<EstadoImpl> getListaEstados(@RequestParam("paisid") Long paisId) throws ErroNoProcessoException {
		Pais pais = repopais.findOne(paisId);
		if (pais==null) throw new ErroNoProcessoException(String.format("País (%s) não encontrado!",paisId));
		return repoestado.findByPaisEquals(pais);
		  
	}

	@GetMapping("listCidades")
	public List<CidadeImpl> getListaCidades(@RequestParam("estadoid") Long estadoId) throws ErroNoProcessoException {
		Estado estado = repoestado.findOne(estadoId);
		if (estado==null) throw new ErroNoProcessoException(String.format("Estado (%s) não encontrado!",estadoId));
		return repocidade.findByEstadoEquals(estado);
		  
	}

}
