package uk.gov.ons.fsdr.report.service;

import com.opencsv.CSVWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.ons.fsdr.report.entity.Report;
import uk.gov.ons.fsdr.report.repository.ReportRepository;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CsvService {

  @Autowired
  private ReportRepository reportRepository;

  public String buildCsv() throws IOException {
    log.debug("building csv");
    final Report nonEmployeeActionsTime = reportRepository.findById("<N/A>").orElseThrow();
    final List<Report> employeesTimes = reportRepository.findAllByUniqueEmployeeIdNot("<N/A>");
    List<String[]> csvRows = new ArrayList<>();
    csvRows.add(buildCsvHeaderRow());
    for (Report employeesTime : employeesTimes) {
      csvRows.add(buildCsvRow(employeesTime, nonEmployeeActionsTime));
    }
    return buildCsv(csvRows);
  }

  private String buildCsv(List<String[]> csvRows) throws IOException {
    StringWriter writer = new StringWriter();
    try (CSVWriter csvWriter = new CSVWriter(writer, ',', '"', '\\', "\n")) {
      csvWriter.writeAll(csvRows);
      return writer.toString();
    }
  }

  private String[] buildCsvRow(Report employeesTime, Report actionsTime) {
    String[] times = new String[42];
    times[0] = employeesTime.getUniqueEmployeeId();
    times[1] = actionsTime.getStartTime();
    times[2] = employeesTime.getJobTitle();
    times[3] = employeesTime.getIngestTime();
    times[4] = actionsTime.getAdeccoIngestStart();
    times[5] = actionsTime.getAdeccoIngestComplete();
    times[6] = employeesTime.getAdeccoCreateEmployeeStart();
    times[7] = employeesTime.getAdeccoCreateEmployeeComplete();
    times[8] = actionsTime.getNisraIngestCsvStart();
    times[9] = actionsTime.getNisraIngestCsvComplete();
    times[10] = employeesTime.getNisraCreateEmployeeStart();
    times[11] = employeesTime.getNisraCreateEmployeeComplete();
    times[12] = actionsTime.getNisraExtractStart();
    times[13] = actionsTime.getNisraExtractComplete();
    times[14] = actionsTime.getActionsStart();
    times[15] = actionsTime.getActionsComplete();
    times[16] = employeesTime.getGsuiteActionStart();
    times[17] = employeesTime.getGsuiteActionComplete();
    times[18] = employeesTime.getGsuiteCreateStart();
    times[19] = employeesTime.getGsuiteCreateComplete();
    times[20] = employeesTime.getGsuiteAreaGroupStart();
    times[21] = employeesTime.getGsuiteAreaGroupComplete();
    times[22] = employeesTime.getGsuiteCoordinatorGroupStart();
    times[23] = employeesTime.getGsuiteCoordinatorGroupComplete();
    times[24] = employeesTime.getGsuiteAllUserGroupStart();
    times[25] = employeesTime.getGsuiteAllUserGroupComplete();
    times[26] = employeesTime.getGsuiteSurveyTypeGroupStart();
    times[27] = employeesTime.getGsuiteSurveyTypeGroupComplete();
    times[28] = employeesTime.getGsuiteTeamDriveStart();
    times[29] = employeesTime.getGsuiteTeamDriveComplete();
    times[30] = employeesTime.getXmaStart();
    times[31] = employeesTime.getXmaComplete();
    times[32] = actionsTime.getXmaDevicesStart();
    times[33] = actionsTime.getXmaDevicesComplete();
    times[34] = actionsTime.getGranbyStart();
    times[35] = actionsTime.getGranbyComplete();
    times[36] = employeesTime.getSnowStart();
    times[37] = employeesTime.getSnowComplete();
    times[38] = actionsTime.getRcaStart();
    times[39] = actionsTime.getRcaComplete();
    times[40] = actionsTime.getLwsStart();
    times[41] = actionsTime.getLwsComplete();
    return times;
  }

  private String[] buildCsvHeaderRow() {
    String[] header = new String[42];

    header[0] = "employee ID";
    header[1] = "start time";
    header[2] = "job title";
    header[3] = "ingest time";
    header[4] = "adecco ingest start time";
    header[5] = "adecco ingest start time";
    header[6] = "adecco create employee start time";
    header[7] = "adecco create employee end time";
    header[8] = "nisra ingest csv start time";
    header[9] = "nisra ingest csv complete time";
    header[10] = "nisra create start time";
    header[11] = "nisra create complete time";
    header[12] = "nisra extract start time";
    header[13] = "nisra extract complete time";
    header[14] = "create actions start time";
    header[15] = "create actions complete time";
    header[16] = "gsuite actions start time";
    header[17] = "gsuite actions complete time";
    header[18] = "gsuite create start time";
    header[19] = "gsuite create complete time";
    header[20] = "gsuite area group start time";
    header[21] = "gsuite area group complete time";
    header[22] = "gsuite coordinator group start time";
    header[23] = "gsuite coordinator group complete time";
    header[24] = "gsuite all users group start time";
    header[25] = "gsuite all users group complete time";
    header[26] = "gsuite survey type group start time";
    header[27] = "gsuite survey type group complete time";
    header[28] = "gsuite team drive start time";
    header[29] = "gsuite team drive complete time";
    header[30] = "xma start time";
    header[31] = "xma complete time";
    header[32] = "xma devices start time";
    header[33] = "xma devices complete time";
    header[34] = "granby extract start time";
    header[35] = "granby extract complete time";
    header[36] = "snow extract start time";
    header[37] = "snow extract complete time";
    header[38] = "rca extract start time";
    header[39] = "rca extract complete time";
    header[40] = "lws extract start time";
    header[41] = "lws extract complete time";

    return header;
  }

}
