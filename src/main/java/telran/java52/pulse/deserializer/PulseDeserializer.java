package telran.java52.pulse.deserializer;

import org.apache.kafka.common.serialization.Deserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import telran.java52.pulse.dto.PulseDto;

@AllArgsConstructor
public class PulseDeserializer implements Deserializer<PulseDto> {

	final ObjectMapper mapper;

	@Override
	public PulseDto deserialize(String topic, byte[] data) {
		try {
			return mapper.readValue(data, PulseDto.class);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

}
