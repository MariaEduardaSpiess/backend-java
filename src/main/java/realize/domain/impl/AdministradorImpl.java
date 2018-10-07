package realize.domain.impl;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A")
//@JsonSerialize(using=VoluntarioSerializer.class)
public class AdministradorImpl extends UsuarioImpl {

	private static final long serialVersionUID = 1L;
	

}
