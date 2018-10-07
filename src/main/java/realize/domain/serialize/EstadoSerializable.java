package realize.domain.serialize;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import realize.domain.impl.EstadoImpl;

public class EstadoSerializable extends JsonSerializer<EstadoImpl> {

	@Override
	public void serialize(EstadoImpl estado, JsonGenerator jsonGen, SerializerProvider serProv)
			throws IOException, JsonProcessingException {
		jsonGen.writeStartObject();
        jsonGen.writeNumberField("id", estado.getId());
        jsonGen.writeStringField("uf", estado.getUf());
        jsonGen.writeStringField("nome", estado.getNome());
        jsonGen.writeEndObject();

	}

}
