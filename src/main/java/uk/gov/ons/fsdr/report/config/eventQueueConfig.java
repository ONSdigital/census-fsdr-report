package uk.gov.ons.fsdr.report.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class eventQueueConfig {

  @Bean
  public AmqpAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
    return new RabbitAdmin(connectionFactory);
  }

  @Bean
  public Queue eventQueue() {
    return QueueBuilder.durable("report.events").build();
  }

  @Bean
  public FanoutExchange eventExchange() {
    return new FanoutExchange("Gateway.Events.Exchange");
  }

  @Bean
  public Binding eventBinding(Queue queue, FanoutExchange fanoutExchange) {
    return BindingBuilder.bind(queue).to(fanoutExchange);
  }
}
