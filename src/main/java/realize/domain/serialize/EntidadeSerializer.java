package realize.domain.serialize;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import realize.domain.impl.EntidadeImpl;

public class EntidadeSerializer extends JsonSerializer<EntidadeImpl> {

	@Override
	public void serialize(EntidadeImpl ent, JsonGenerator jsonGen, SerializerProvider serProv)
			throws IOException, JsonProcessingException {
		jsonGen.writeStartObject();
        jsonGen.writeNumberField("id", ent.getId());
        jsonGen.writeStringField("celular", ent.getCelular());
        jsonGen.writeStringField("documento", ent.getDocumento());
        jsonGen.writeStringField("email", ent.getEmail());
        jsonGen.writeStringField("endBairro", ent.getEndBairro());
        jsonGen.writeStringField("endCidade", ent.getEndCidade());
        jsonGen.writeStringField("endComplemento", ent.getEndComplemento());
        jsonGen.writeStringField("endEstado", ent.getEndEstado());
        jsonGen.writeStringField("endLogradouro", ent.getEndLogradouro());
        jsonGen.writeStringField("endPais", ent.getEndPais());
        jsonGen.writeStringField("nomeCompleto", ent.getNomeCompleto());
        jsonGen.writeStringField("telefone", ent.getTelefone());
        jsonGen.writeStringField("tipo", ent.getTipo());
        jsonGen.writeStringField("userPassword", ent.getUserPassword());
        jsonGen.writeStringField("endcep", ""+ent.getEndCep());
        jsonGen.writeStringField("endNumero", ""+ent.getEndNumero());
        jsonGen.writeStringField("version", ""+ent.getVersion());
        jsonGen.writeStringField("descricao", ""+ent.getDescricao());
        jsonGen.writeEndObject();
	}

}
