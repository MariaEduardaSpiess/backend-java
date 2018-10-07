package realize.endpoint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import realize.domain.impl.EntidadeImpl;
import realize.domain.impl.TimeVoluntarioImpl;
import realize.domain.impl.VoluntarioImpl;
import realize.endpoint.core.AbstractCRUDEndpoint;
import realize.endpoint.core.FilteredRepository;
import realize.endpoint.core.ValidaToken;
import realize.exeptions.ErroNoProcessoException;
import realize.exeptions.InvalidAuthException;
import realize.repo.TimeVoluntarioRepository;
import realize.repo.VoluntarioRepository;

@RestController
@RequestMapping( "/api/private/v1/timevoluntario" )
public class TimeVoluntarioEndpoint extends AbstractCRUDEndpoint<TimeVoluntarioImpl, Long> {

	@Autowired
	private TimeVoluntarioRepository repo;

	@Autowired
	private VoluntarioRepository repovol;

	@Autowired
	private ValidaToken validaToken;
	
	@Override
	public FilteredRepository<TimeVoluntarioImpl, Long> getRepository() {
		return repo;
	}

	@GetMapping("time")
	public List<TimeVoluntarioImpl> time(HttpServletRequest request) throws InvalidAuthException, ErroNoProcessoException {
		validaToken.valida(request);
		EntidadeImpl entidade = validaToken.getEntidade();
		List<TimeVoluntarioImpl> list = repo.findByEntidade(entidade);
		Collections.sort(list, new Comparator<TimeVoluntarioImpl>() {
			@Override
			public int compare(TimeVoluntarioImpl o1, TimeVoluntarioImpl o2) {
				return o1.getVoluntario().getNomeCompleto().compareTo(o2.getVoluntario().getNomeCompleto());
			}
		});
		return list; 
	}
	
	@GetMapping("buscanovosvoluntariosparaotime")
	public List<VoluntarioImpl> buscaNovosVoluntariosParaOTime(HttpServletRequest request) throws InvalidAuthException, ErroNoProcessoException {
		List<VoluntarioImpl> list = new ArrayList<>();
		validaToken.valida(request);
		EntidadeImpl entidade = validaToken.getEntidade();
		List<TimeVoluntarioImpl> listTimeAtual = repo.findByEntidade(entidade);
		List<VoluntarioImpl> listVol = repovol.findAll();
		for (VoluntarioImpl vol : listVol) {
			boolean achou = false;
			for (TimeVoluntarioImpl timevol : listTimeAtual) {
				if (timevol.getVoluntario().getId().equals(vol.getId())) {
					achou = true;
					break;
				}
			}
			if (!achou) {
				list.add(vol);
			}
		}
		
		return list;
	}
}
