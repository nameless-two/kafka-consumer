package telran.java52.pulse.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import telran.java52.pulse.dto.PulseDto;

@Service
public class KafkaConsumerService {

	@KafkaListener(topics = "java52-default-topic", groupId = "antstein-group1")
	public void consume(PulseDto pulse) {
		System.out.println("pulse recieved: " + pulse);
	}
}
