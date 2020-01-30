package uk.gov.ons.fsdr.report.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.ons.census.fwmt.events.data.GatewayEventDTO;

import java.io.IOException;

import static uk.gov.ons.fsdr.report.config.eventQueueConfig.EVENTS_QUEUE;

@Service
public class ReportService {

  @Autowired ObjectMapper objectMapper;

  @RabbitListener(queues = EVENTS_QUEUE)
  public void readMessage(Message message) throws IOException {
    GatewayEventDTO event = objectMapper.readValue(message.getBody(), GatewayEventDTO.class);
    System.out.println(event.toString());

  }
}
