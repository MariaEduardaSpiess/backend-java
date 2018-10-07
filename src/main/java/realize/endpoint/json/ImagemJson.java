package realize.endpoint.json;

import javax.persistence.Lob;

public class ImagemJson {


	private Long id;
	
	@Lob
	private String imagem;

	public ImagemJson() {
	}

	public ImagemJson(Long id, String imagem) {
		this.id = id;
		this.imagem = imagem;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	public String getImagem() {
		return imagem;
	}
}
