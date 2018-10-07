package realize.domain.serialize;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import realize.domain.impl.PaisImpl;

public class PaisSerializable extends JsonSerializer<PaisImpl> {

	@Override
	public void serialize(PaisImpl pais, JsonGenerator jsonGen, SerializerProvider serProv)
			throws IOException, JsonProcessingException {
		jsonGen.writeStartObject();
        jsonGen.writeNumberField("id", pais.getId());
        jsonGen.writeStringField("sigla", pais.getSigla());
        jsonGen.writeStringField("nome", pais.getNome());
        jsonGen.writeEndObject();

	}

}
