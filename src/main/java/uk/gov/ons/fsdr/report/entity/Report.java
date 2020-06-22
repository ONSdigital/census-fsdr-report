package uk.gov.ons.fsdr.report.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "report")
@Data
@NoArgsConstructor
public class Report {
  @Id
  @Column(name = "unique_employee_id")
  private String uniqueEmployeeId;

  @Column(name = "start_time")
  private String startTime;

  @Column(name = "job_title")
  private String jobTitle;

  @Column(name = "ingest_time")
  private String ingestTime;

  @Column(name = "adecco_create_employee_start")
  private String adeccoCreateEmployeeStart;

  @Column(name = "adecco_create_employee_complete")
  private String adeccoCreateEmployeeComplete;

  @Column(name = "adecco_ingest_start")
  private String adeccoIngestStart;

  @Column(name = "adecco_ingest_complete")
  private String adeccoIngestComplete;

  @Column(name = "nisra_create_start")
  private String nisraCreateEmployeeStart;

  @Column(name = "nisra_create_complete")
  private String nisraCreateEmployeeComplete;

  @Column(name = "nisra_ingest_csv_start")
  private String nisraIngestCsvStart;

  @Column(name = "nisra_ingest_csv_complete")
  private String nisraIngestCsvComplete;

  @Column(name = "nisra_extract_start")
  private String nisraExtractStart;

  @Column(name = "nisra_extract_complete")
  private String nisraExtractComplete;

  @Column(name = "actions_start")
  private String actionsStart;

  @Column(name = "actions_complete")
  private String actionsComplete;

  @Column(name = "action_type")
  @Enumerated(EnumType.STRING)
  private ActionType actionType;

  @Column(name = "gsuite_action_start")
  private String gsuiteActionStart;

  @Column(name = "gsuite_action_complete")
  private String gsuiteActionComplete;

  @Column(name = "gsuite_create_start")
  private String gsuiteCreateStart;

  @Column(name = "gsuite_create_complete")
  private String gsuiteCreateComplete;

  @Column(name = "gsuite_area_group_start")
  private String gsuiteAreaGroupStart;

  @Column(name = "gsuite_area_group_complete")
  private String gsuiteAreaGroupComplete;

  @Column(name = "gsuite_coordinator_group_start")
  private String gsuiteCoordinatorGroupStart;

  @Column(name = "gsuite_coordinator_group_complete")
  private String gsuiteCoordinatorGroupComplete;

  @Column(name = "gsuite_all_user_group_start")
  private String gsuiteAllUserGroupStart;

  @Column(name = "gsuite_all_user_group_complete")
  private String gsuiteAllUserGroupComplete;

  @Column(name = "gsuite_survey_type_group_start")
  private String gsuiteSurveyTypeGroupStart;

  @Column(name = "gsuite_survey_type_group_complete")
  private String gsuiteSurveyTypeGroupComplete;

  @Column(name = "gsuite_team_drive_start")
  private String gsuiteTeamDriveStart;

  @Column(name = "gsuite_team_drive_complete")
  private String gsuiteTeamDriveComplete;

  @Column(name = "xma_start")
  private String xmaStart;

  @Column(name = "xma_complete")
  private String xmaComplete;

  @Column(name = "xma_devices_start")
  private String xmaDevicesStart;

  @Column(name = "xma_devices_complete")
  private String xmaDevicesComplete;

  @Column(name = "granby_start")
  private String granbyStart;

  @Column(name = "granby_complete")
  private String granbyComplete;

  @Column(name = "snow_start")
  private String serviceNowStart;

  @Column(name = "snow_complete")
  private String serviceNowComplete;

  @Column(name = "rca_start")
  private String rcaStart;

  @Column(name = "rca_complete")
  private String rcaComplete;

  @Column(name = "lws_start")
  private String lwsStart;

  @Column(name = "lws_complete")
  private String lwsComplete;

  @Version
  @Column(name = "VERSION", columnDefinition = "integer DEFAULT 0", nullable = false)
  private long version;

  public Report(String uniqueEmployeeId) {
    this.uniqueEmployeeId = uniqueEmployeeId;
  }
}
