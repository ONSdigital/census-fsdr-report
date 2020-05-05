package uk.gov.ons.fsdr.report.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import uk.gov.ons.census.fwmt.events.component.GatewayEventManager;
import uk.gov.ons.census.fwmt.events.data.GatewayErrorEventDTO;
import uk.gov.ons.census.fwmt.events.data.GatewayEventDTO;
import uk.gov.ons.fsdr.report.entity.ActionType;
import uk.gov.ons.fsdr.report.entity.Report;
import uk.gov.ons.fsdr.report.repository.ReportRepository;

import static uk.gov.ons.fsdr.report.config.GatewayEventsConfig.FSDR_REPORT_READY;
import static uk.gov.ons.fsdr.report.config.eventQueueConfig.EVENTS_QUEUE;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@RabbitListener(queues = EVENTS_QUEUE)
@Slf4j
public class ReportService {

  @Autowired
  private ReportRepository reportRepository;

  @Autowired
  private GatewayEventManager eventManager;

  @Autowired
  private AmqpAdmin rabbitAdmin;

  @Value("${report.timeout}")
  private long timeToWait;

  @RabbitHandler
  public void readMessage(GatewayErrorEventDTO event) {
    //this method is intentionally empty so that spring doesn't throw an exception
    // when there is an GatewayErrorEventDTO message on the queue
  }

  @RabbitHandler
  public void readMessage(GatewayEventDTO event) {

    String caseId = event.getCaseId();
    log.debug("processing event: {} for ID: {}", event.getEventType(), caseId);
    Report report = reportRepository.findById(caseId)
        .orElseGet(() -> reportRepository.saveAndFlush(new Report(caseId)));
    updateReport(report, event);
  }

  private void updateReport(Report report, GatewayEventDTO gatewayEventDTO) {
    String eventTime = gatewayEventDTO.getMetadata().get("TS");

    switch (gatewayEventDTO.getEventType()) {
    case "JOB_TYPE":
      report.setJobTitle(gatewayEventDTO.getMetadata().get("JobRole Type"));
      break;
    case "FSDR_PROCESS_STARTED":
      report.setStartTime(eventTime);
      break;
    case "RECEIVED_GSUITE_ACTION_MESSAGE":
      report.setGsuiteActionStart(eventTime);
      break;
    case "SENDING_GSUITE_ACTION_RESPONSE":
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
    case "RECEIVED_SNOW_ACTION_MESSAGE":
      report.setSnowStart(eventTime);
      break;
    case "SENDING_SERVICE_NOW_ACTION_RESPONSE":
      report.setSnowComplete(eventTime);
      break;
    case "RECEIVED_XMA_ACTION_MESSAGE":
      report.setXmaStart(eventTime);
      break;
    case "SENDING_XMA_ACTION_RESPONSE":
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
    case "RECEIVED_LWS_ACTION_MESSAGE":
      report.setLwsStart(eventTime);
      break;
    case "SENDING_LWS_ACTION_RESPONSE":
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
    case "LOGISTICS_EXTRACT_STARTED":
      report.setGranbyStart(eventTime);
      break;
    case "LOGISTICS_EXTRACT_COMPLETE":
      report.setGranbyComplete(eventTime);
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
    case "FSDR_COMPLETE":
      boolean retryResult = checkEventQueue(timeToWait);
      if (retryResult) {
        eventManager.triggerEvent("<N/A>", FSDR_REPORT_READY);
      } else {
        log.error("event queue did not finish processing in {}ms, report may need to be generated manually",
            timeToWait);
      }
    default:
      return;
    }
    reportRepository.saveAndFlush(report);
  }

  private boolean checkIfQueueEmpty() {
    int eventQueueCount = (int) rabbitAdmin.getQueueProperties(EVENTS_QUEUE).get("QUEUE_MESSAGE_COUNT");
    return eventQueueCount == 0;
  }

  private boolean checkEventQueue(long timeout) {
    long startTime = System.currentTimeMillis();
    boolean keepChecking = true;

    while (keepChecking) {
      boolean result = checkIfQueueEmpty();

      if (result) {
        return true;
      }

      long elapsedTime = System.currentTimeMillis() - startTime;
      if (elapsedTime > timeout) {
        keepChecking = false;
      } else {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
    return false;
  }




}
