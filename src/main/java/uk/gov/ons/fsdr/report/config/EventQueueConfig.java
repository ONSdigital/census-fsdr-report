package uk.gov.ons.fsdr.report.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class EventQueueConfig {
	  public static final String EVENTS_TOPIC_QUEUE = "Report.Events";
	  public static final String FFA_EVENTS_EXCHANGE = "FFA.Events.Exchange";

  @Bean
  public AmqpAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
    return new RabbitAdmin(connectionFactory);
  }

  @Bean
  public Queue eventTopicQueue() {
    return QueueBuilder.durable(EVENTS_TOPIC_QUEUE).build();
  }

  @Bean
  public Declarables reportEventTopicBinding(@Qualifier("eventTopicQueue") Queue reportEventTopicQueue, @Qualifier("reportEventsTopicExchange") TopicExchange reportEventsTopicExchange) {
	    return new Declarables(reportEventTopicQueue, reportEventsTopicExchange,
    		BindingBuilder.bind(reportEventTopicQueue).to(reportEventsTopicExchange).with("FSDR.SCHEDULAR"),
    		BindingBuilder.bind(reportEventTopicQueue).to(reportEventsTopicExchange).with("PROCESSOR.ADECCO"),
    		BindingBuilder.bind(reportEventTopicQueue).to(reportEventsTopicExchange).with("INGEST.ADECCO"),
    		BindingBuilder.bind(reportEventTopicQueue).to(reportEventsTopicExchange).with("PROCESSOR.EMPLOYEE"),
    		BindingBuilder.bind(reportEventTopicQueue).to(reportEventsTopicExchange).with("SERVICE.FSDR"),
    		BindingBuilder.bind(reportEventTopicQueue).to(reportEventsTopicExchange).with("ACTION.PRODUCED.HQ"),
//    		BindingBuilder.bind(reportEventTopicQueue).to(reportEventsTopicExchange).with("ACTION.PRODUCED.#"),
    		BindingBuilder.bind(reportEventTopicQueue).to(reportEventsTopicExchange).with("EXTRACT.LOGISTICS"),
    		BindingBuilder.bind(reportEventTopicQueue).to(reportEventsTopicExchange).with("EXTRACT.NISRA"),
    		BindingBuilder.bind(reportEventTopicQueue).to(reportEventsTopicExchange).with("INGEST.NISRA"),
    		BindingBuilder.bind(reportEventTopicQueue).to(reportEventsTopicExchange).with("EXTRACT.RCA"),
    		BindingBuilder.bind(reportEventTopicQueue).to(reportEventsTopicExchange).with("ACTION.RECEIVED.XMA"),
    		BindingBuilder.bind(reportEventTopicQueue).to(reportEventsTopicExchange).with("ACTION.RESPONSE.PRODUCER.XMA"),
    		BindingBuilder.bind(reportEventTopicQueue).to(reportEventsTopicExchange).with("ACTION.RECEIVED.SERVICE_NOW"),
    		BindingBuilder.bind(reportEventTopicQueue).to(reportEventsTopicExchange).with("ACTION.RESPONSE.PRODUCER.SERVICE_NOW"),
    		BindingBuilder.bind(reportEventTopicQueue).to(reportEventsTopicExchange).with("ACTION.RECEIVED.LWS"),
    		BindingBuilder.bind(reportEventTopicQueue).to(reportEventsTopicExchange).with("ACTION.RECEIVED.GSUITE"),
    		BindingBuilder.bind(reportEventTopicQueue).to(reportEventsTopicExchange).with("ACTION.RESPONSE.PRODUCER.GSUITE"),
    		BindingBuilder.bind(reportEventTopicQueue).to(reportEventsTopicExchange).with("SERVICE.GSUITE"),
    		BindingBuilder.bind(reportEventTopicQueue).to(reportEventsTopicExchange).with("MOCK.FSDR")
			  );
  }
  
  @Bean
  public Jackson2JsonMessageConverter messageConverter() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    return new Jackson2JsonMessageConverter(objectMapper);
  }

  
  @Bean("reportEventsTopicExchange")
  public TopicExchange reportEventsTopicExchange() {
	  TopicExchange topicExchange = new TopicExchange(FFA_EVENTS_EXCHANGE);
    return topicExchange;
  }
}
