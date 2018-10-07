package realize.domain.serialize;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import realize.domain.impl.SegmentoImpl;

public class SegmentoSerializer extends JsonSerializer<SegmentoImpl> {

	@Override
	public void serialize(SegmentoImpl seg, JsonGenerator jsonGen, SerializerProvider serProv)
			throws IOException, JsonProcessingException {
		jsonGen.writeStartObject();
        jsonGen.writeNumberField("id", seg.getId());
        jsonGen.writeStringField("nome", seg.getNome());
        jsonGen.writeEndObject();
	}

	
}
