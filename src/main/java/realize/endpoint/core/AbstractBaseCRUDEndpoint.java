package realize.endpoint.core;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import realize.exeptions.ErroNoProcessoException;
import realize.exeptions.InvalidAuthException;
import realize.exeptions.ValidationException;

public abstract class AbstractBaseCRUDEndpoint<T,ID extends Serializable,RID extends Serializable> {

	@GetMapping
	@Transactional(readOnly=true)
	public List<T> findAll(@RequestHeader Map<String,String> header, 
			@RequestParam(name="filtro",required=false) String filtro) throws InvalidAuthException {
//		tksb.validateAuth(header);
		Stream<T> stream=null;
		FilteredRepository<T, RID> repo = getRepository();
		if (filtro==null) stream = repo.findAll().stream();
		else stream=repo.findAllFiltered(filtro);
		return stream.collect(Collectors.toList());
	}

	@PutMapping 
	@Transactional(rollbackFor=Exception.class)
	public T insert(@RequestHeader Map<String,String> header, 
			@RequestBody T obj) throws InvalidAuthException, ErroNoProcessoException, ValidationException {

		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Object>> violations = validator.validate(obj);
		if (!violations.isEmpty()) throw new ValidationException(violations);
//		tksb.validateAuth(header);
		prepareDataBeforePersist(obj);
		FilteredRepository<T, RID> repo = getRepository();
		validateBeforePersist(obj);
		return repo.save(obj);
	}
	
	@PostMapping
	@Transactional(rollbackFor=Exception.class)
	public T update(@RequestHeader Map<String,String> header, 
			@RequestBody T obj) throws InvalidAuthException, ErroNoProcessoException, ValidationException {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Object>> violations = validator.validate(obj);
		if (!violations.isEmpty()) throw new ValidationException(violations);
//		tksb.validateAuth(header);
		prepareDataBeforePersist(obj);
		FilteredRepository<T, RID> repo = getRepository();
		validateBeforePersist(obj);
		return repo.save(obj);
	}

	@DeleteMapping
	@Transactional(rollbackFor=Exception.class)
	public void delete(@RequestHeader Map<String,String> header, 
			@RequestBody T obj) throws InvalidAuthException, ErroNoProcessoException {
//		tksb.validateAuth(header);
		FilteredRepository<T, RID> repo = getRepository();
		validateBeforePersist(obj);
		repo.delete(obj);
	}
	
	public abstract FilteredRepository<T, RID> getRepository();

	public void prepareDataBeforePersist(T obj) {
	}

	public void validateBeforePersist(T obj) throws ErroNoProcessoException {
	}


}
