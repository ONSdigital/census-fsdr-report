package uk.gov.ons.fsdr.report.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.ons.census.fwmt.events.data.GatewayEventDTO;
import uk.gov.ons.fsdr.report.entity.ActionType;
import uk.gov.ons.fsdr.report.entity.Report;
import uk.gov.ons.fsdr.report.repository.ReportRepository;

import java.io.IOException;
import java.time.LocalDateTime;

import static uk.gov.ons.fsdr.report.config.eventQueueConfig.EVENTS_QUEUE;

@Service
public class ReportService {

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private ReportRepository reportRepository;

  @RabbitListener(queues = EVENTS_QUEUE)
  public void readMessage(Message message) throws IOException {
    GatewayEventDTO event = objectMapper.readValue(message.getBody(), GatewayEventDTO.class);
    System.out.println(event.toString());

    String caseId = event.getCaseId();
    reportRepository.findById(caseId).ifPresentOrElse(
        rep -> updateReport(rep, event),
        () -> newReport(event)
    );
  }

  private void newReport(GatewayEventDTO gatewayEventDTO) {
    Report report = new Report();
    report.setUniqueEmployeeId(gatewayEventDTO.getCaseId());
    updateReport(report, gatewayEventDTO);
  }

  private void updateReport(Report report, GatewayEventDTO gatewayEventDTO) {
    String ts = gatewayEventDTO.getMetadata().get("TS");
    LocalDateTime eventTime = null;
    if (ts != null) {
      eventTime = LocalDateTime.parse(ts);
    }
    switch (gatewayEventDTO.getEventType()) {
    case "JOB_TYPE":
      report.setJobTitle(gatewayEventDTO.getMetadata().get("JobRole Type"));
      break;
    case "GSUITE_ACTION_STARTED":
      report.setGsuiteActionStart(eventTime);
      break;
    case "GSUITE_ACTION_COMPLETE":
      report.setGsuiteActionComplete(eventTime);
      break;
    case "GSUITE_USER_CREATE_STARTED":
      report.setGsuiteCreateStart(eventTime);
      break;
    case "GSUITE_USER_CREATED":
      report.setGsuiteCreateComplete(eventTime);
      break;
    case "GSUITE_AREAGROUP_STARTED":
      report.setGsuiteAreaGroupStart(eventTime);
      break;
    case "GSUITE_AREAGROUP_ADDED":
      report.setGsuiteAreaGroupComplete(eventTime);
      break;
    case "GSUITE_COORDGROUP_STARTED":
      report.setGsuiteCoordinatorGroupStart(eventTime);
      break;
    case "GSUITE_COORDGROUP_ADDED":
      report.setGsuiteCoordinatorGroupComplete(eventTime);
      break;
    case "GSUITE_ALLUSERSGROUP_STARTED":
      report.setGsuiteAllUserGroupStart(eventTime);
      break;
    case "GSUITE_ALLUSERSGROUP_ADDED":
      report.setGsuiteAllUserGroupComplete(eventTime);
      break;
    case "GSUITE_SURVEYTYPE_STARTED":
      report.setGsuiteSurveyTypeGroupStart(eventTime);
      break;
    case "GSUITE_SURVEYTYPE_ADDED":
      report.setGsuiteSurveyTypeGroupComplete(eventTime);
      break;
    case "GSUITE_DRIVEGROUP_STARTED":
      report.setGsuiteTeamDriveStart(eventTime);
      break;
    case "GSUITE_DRIVEGROUP_ADDED":
      report.setGsuiteTeamDriveComplete(eventTime);
      break;
    case "ADECCO_CREATION_STARTED":
      report.setAdeccoCreateEmployeeStart(eventTime);
      report.setIngestTime(eventTime);
      break;
    case "ADECCO_CREATION_COMPLETE":
      report.setAdeccoCreateEmployeeComplete(eventTime);
      break;
    case "ADECCO_INGEST_STARTED":
      report.setAdeccoIngestStart(eventTime);
      report.setStartTime(eventTime);
      break;
    case "ADECCO_INGEST_COMPLETE":
      report.setAdeccoIngestComplete(eventTime);
      break;
    case "NISRA_CREATION_STARTED":
      report.setNisraCreateEmployeeStart(eventTime);
      report.setIngestTime(eventTime);
      break;
    case "NISRA_CREATION_COMPLETE":
      report.setNisraCreateEmployeeComplete(eventTime);
      break;
    case "SERVICENOW_ACTION_STARTED":
      report.setSnowStart(eventTime);
      break;
    case "SERVICENOW_ACTION_COMPLETE":
      report.setSnowComplete(eventTime);
      break;
    case "XMA_ACTION_STARTED":
      report.setXmaStart(eventTime);
      break;
    case "XMA_ACTION_COMPLETE":
      report.setXmaComplete(eventTime);
      break;
    case "FSDR_PROCESSES_ACTIONS_STARTED":
      report.setActionsStart(eventTime);
      break;
    case "FSDR_PROCESSES_ACTIONS_COMPLETE":
      report.setActionsComplete(eventTime);
      break;
    case "NISRA_EXTRACT_STARTED":
      report.setNisraExtractStart(eventTime);
      break;
    case "NISRA_EXTRACT_COMPLETE":
      report.setNisraExtractComplete(eventTime);
      break;
    case "LWS_EXTRACT_STARTED":
      report.setLwsStart(eventTime);
      break;
    case "LWS_EXTRACT_COMPLETE":
      report.setLwsComplete(eventTime);
      break;
    case "XMA_DEVICES_STARTED":
      report.setXmaDevicesStart(eventTime);
      break;
    case "XMA_DEVICES_COMPLETE":
      report.setXmaDevicesComplete(eventTime);
      break;
    case "NISRA_INGEST_STARTED":
      report.setNisraIngestCsvStart(eventTime);
      report.setStartTime(eventTime);
      break;
    case "NISRA_INGEST_COMPLETE":
      report.setNisraIngestCsvComplete(eventTime);
      break;
    case "RCA_EXTRACT_STARTED":
      report.setRcaStart(eventTime);
      break;
    case "RCA_EXTRACT_COMPLETE":
      report.setRcaComplete(eventTime);
      break;
    case "CREATED_CREATE_ACTION":
      report.setActionType(ActionType.CREATE);
      break;
    case "CREATED_UPDATE_ACTION":
      report.setActionType(ActionType.UPDATE);
      break;
    case "CREATED_MOVER_ACTION":
      report.setActionType(ActionType.MOVER);
      break;
    case "CREATED_LEAVER_ACTION":
      report.setActionType(ActionType.LEAVER);
      break;
    default:
      return;
    }
    reportRepository.saveAndFlush(report);
  }
}
