package uk.gov.ons.fsdr.report.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.ons.fsdr.report.entity.Report;
import uk.gov.ons.fsdr.report.repository.ReportRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CsvService {

  @Autowired ReportRepository reportRepository;
  
  public String buildCsv() {
    final Report nonEmployeeActionsTime = reportRepository.findById("<N/A>").orElseThrow();
    final List<Report> employeesTimes = reportRepository.findAllByUniqueEmployeeIdNot("<N/A>");
    List<String[]> csvRows = new ArrayList<>();
    csvRows.add(buildCsvHeaderRow());
    for (Report employeesTime: employeesTimes) {
      csvRows.add(buildCsvRow(employeesTime, nonEmployeeActionsTime));
    }
    return null;
  }

  private String[] buildCsvRow(Report employeesTime, Report actionsTime) {
    String[] times = new String[47];
    times[0] = employeesTime.getUniqueEmployeeId();
    times[1] = String.valueOf(employeesTime.getStartTime());
    times[2] = employeesTime.getJobTitle();
    times[3] = String.valueOf(employeesTime.getIngestTime());
    times[4] = String.valueOf(actionsTime.getAdeccoIngestStart());
    times[5] = String.valueOf(actionsTime.getAdeccoIngestComplete());
    times[6] = String.valueOf(employeesTime.getAdeccoCreateEmployeeStart());
    times[7] = String.valueOf(employeesTime.getAdeccoCreateEmployeeComplete());
    times[8] = String.valueOf(actionsTime.getNisraIngestCsvStart());
    times[9] = String.valueOf(actionsTime.getNisraIngestCsvComplete());
    times[10] = String.valueOf(employeesTime.getNisraCreateEmployeeStart());
    times[11] = String.valueOf(employeesTime.getNisraCreateEmployeeComplete());
    times[12] = String.valueOf(actionsTime.getNisraExtractStart());
    times[13] = String.valueOf(actionsTime.getNisraExtractComplete());
    times[14] = String.valueOf(actionsTime.getActionsStart());
    times[15] = String.valueOf(actionsTime.getActionsComplete());
    times[16] = String.valueOf(employeesTime.getGsuiteActionStart());
    times[17] = String.valueOf(employeesTime.getGsuiteActionComplete());
    times[18] = String.valueOf(employeesTime.getGsuiteCreateStart());
    times[19] = String.valueOf(employeesTime.getGsuiteCreateComplete());
    times[20] = String.valueOf(employeesTime.getGsuiteAreaGroupStart());
    times[21] = String.valueOf(employeesTime.getGsuiteAreaGroupComplete());
    times[22] = String.valueOf(employeesTime.getGsuiteCoordinatorGroupStart());
    times[23] = String.valueOf(employeesTime.getGsuiteCoordinatorGroupComplete());
    times[24] = String.valueOf(employeesTime.getGsuiteAllUserGroupStart());
    times[25] = String.valueOf(employeesTime.getGsuiteAllUserGroupComplete());
    times[26] = String.valueOf(employeesTime.getGsuiteSurveyTypeGroupStart());
    times[27] = String.valueOf(employeesTime.getGsuiteSurveyTypeGroupComplete());
    times[28] = String.valueOf(employeesTime.getGsuiteTeamDriveStart());
    times[29] = String.valueOf(employeesTime.getGsuiteTeamDriveComplete());
    times[30] = String.valueOf(employeesTime.getXmaStart());
    times[31] = String.valueOf(employeesTime.getXmaComplete());
    times[32] = String.valueOf(actionsTime.getXmaDevicesStart());
    times[33] = String.valueOf(actionsTime.getXmaDevicesComplete());
    times[34] = String.valueOf(actionsTime.getGranbyStart());
    times[35] = String.valueOf(actionsTime.getGranbyComplete());
    times[36] = String.valueOf(employeesTime.getSnowStart());
    times[37] = String.valueOf(employeesTime.getSnowComplete());
    times[38] = String.valueOf(actionsTime.getRcaStart());
    times[39] = String.valueOf(actionsTime.getRcaComplete());
    times[40] = String.valueOf(actionsTime.getLwsStart());
    times[41] = String.valueOf(actionsTime.getLwsComplete());
    return times;
  }

  private String[] buildCsvHeaderRow() {
    String[] header = new String[47];

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
