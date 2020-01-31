package uk.gov.ons.fsdr.report.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.ons.census.fwmt.events.data.GatewayEventDTO;
import uk.gov.ons.fsdr.report.entity.Report;
import uk.gov.ons.fsdr.report.repository.ReportRepository;

import java.io.IOException;
import java.time.LocalDateTime;

import static uk.gov.ons.fsdr.report.config.eventQueueConfig.EVENTS_QUEUE;

@Service
public class ReportService {

  @Autowired ObjectMapper objectMapper;

  @Autowired ReportRepository reportRepository;

  @RabbitListener(queues = EVENTS_QUEUE)
  public void readMessage(Message message) throws IOException {
    GatewayEventDTO event = objectMapper.readValue(message.getBody(), GatewayEventDTO.class);
    System.out.println(event.toString());

    String caseId = event.getCaseId();
    if (!caseId.equals("<N/A>")) {
      reportRepository.findById(caseId).ifPresentOrElse(
          rep -> updateReport(rep, event),
          () -> newReport(event)
      );
    }

  }

  private void newReport(GatewayEventDTO gatewayEventDTO) {
    Report report = new Report();
    report.setUniqueEmployeeId(gatewayEventDTO.getCaseId());
    updateReport(report, gatewayEventDTO);
  }

  private void updateReport(Report report, GatewayEventDTO gatewayEventDTO) {
    String ts = gatewayEventDTO.getMetadata().get("TS");
    switch (gatewayEventDTO.getEventType()) {
    case "GSUITE_ACTION_STARTED":
      report.setGsuiteActionStart(LocalDateTime.parse(ts));
      break;
    case "GSUITE_ACTION_COMPLETE":
      report.setGsuiteActionComplete(LocalDateTime.parse(ts));
      break;
    case "GSUITE_USER_CREATE_STARTED":
      report.setGsuiteCreateStart(LocalDateTime.parse(ts));
      break;
    case "GSUITE_USER_CREATED":
      report.setGsuiteCreateComplete(LocalDateTime.parse(ts));
      break;
    case "GSUITE_AREAGROUP_STARTED":
      report.setGsuiteAreaGroupStart(LocalDateTime.parse(ts));
      break;
    case "GSUITE_AREAGROUP_ADDED":
      report.setGsuiteAreaGroupComplete(LocalDateTime.parse(ts));
      break;
    case "GSUITE_COORDGROUP_STARTED":
      report.setGsuiteCoordinatorGroupStart(LocalDateTime.parse(ts));
      break;
    case "GSUITE_COORDGROUP_ADDED":
      report.setGsuiteCoordinatorGroupComplete(LocalDateTime.parse(ts));
      break;
    case "GSUITE_ALLUSERSGROUP_STARTED":
      report.setGsuiteAllUserGroupStart(LocalDateTime.parse(ts));
      break;
    case "GSUITE_ALLUSERSGROUP_ADDED":
      report.setGsuiteAllUserGroupComplete(LocalDateTime.parse(ts));
      break;
    case "GSUITE_SURVEYTYPE_STARTED":
      report.setGsuiteSurveyTypeGroupStart(LocalDateTime.parse(ts));
      break;
    case "GSUITE_SURVEYTYPE_ADDED":
      report.setGsuiteSurveyTypeGroupComplete(LocalDateTime.parse(ts));
      break;
    case "GSUITE_DRIVEGROUP_STARTED":
      report.setGsuiteTeamDriveStart(LocalDateTime.parse(ts));
      break;
    case "GSUITE_DRIVEGROUP_ADDED":
      report.setGsuiteTeamDriveComplete(LocalDateTime.parse(ts));
      break;
    case "ADECCO_CREATION_STARTED":
      report.setAdeccoCreateStart(LocalDateTime.parse(ts));
      break;
    case "ADECCO_CREATION_COMPLETE":
      report.setAdeccoCreateComplete(LocalDateTime.parse(ts));
      break;
    case "NISRA_CREATION_STARTED":
      report.setNisraCreateStart(LocalDateTime.parse(ts));
      break;
    case "NISRA_CREATION_COMPLETE":
      report.setNisraCreateComplete(LocalDateTime.parse(ts));
      break;
    case "SERVICENOW_ACTION_STARTED":
      report.setSnowStart(LocalDateTime.parse(ts));
      break;
    case "SERVICENOW_ACTION_COMPLETE":
      report.setSnowComplete(LocalDateTime.parse(ts));
      break;
    case "XMA_ACTION_STARTED":
      report.setXmaStart(LocalDateTime.parse(ts));
      break;
    case "XMA_ACTION_COMPLETE":
      report.setXmaComplete(LocalDateTime.parse(ts));
      break;
    default:
      return;
    }
    reportRepository.saveAndFlush(report);
  }
}
