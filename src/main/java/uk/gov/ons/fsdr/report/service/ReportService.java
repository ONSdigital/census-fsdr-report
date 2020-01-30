package uk.gov.ons.fsdr.report.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ReportService {
  @RabbitListener
  public void readMessage() {

  }
}
