package training.employees;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EventStoreGateway {

//    private RestTemplate restTemplate;
//
//    @Value("${employees.eventstore.url}")
//    private String url;
//
//    public EventStoreGateway(RestTemplateBuilder builder) {
//        restTemplate = builder.build();
//    }
//
//    public void sendEvent(CreateEventCommand event) {
//        log.debug("Send event to eventstore");
//        restTemplate.postForObject(url, event, String.class);
//    }

//    private final JmsTemplate jmsTemplate;
//
//    public EventStoreGateway(JmsTemplate jmsTemplate) {
//        this.jmsTemplate = jmsTemplate;
//    }
//
//    public void sendEvent(CreateEventCommand event){
//        log.debug("Send event to eventstore");
//        jmsTemplate.convertAndSend("eventsQueue", event);
//    }
}