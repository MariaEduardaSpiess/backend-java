package realize.domain;

import java.io.Serializable;

public interface Usuario extends Endereco, Serializable {
	
	Long getId();
	String getEmail();
	String getNomeCompleto();
	String getUserPassword();
	String getDocumento();
	String getTelefone();
	String getCelular();
	
	String getTipo();
	
	boolean isEnabled();
	
	String getImagem();
	String getDescricao();
	
	Long getVersion();

}
