package uk.gov.ons.fsdr.report.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "report")
@Data
public class Report {
    @Id
    @Column(name = "unique_employee_id")
    private String uniqueEmployeeId;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "ingest_time")
    private LocalDateTime ingestTime;

    @Column(name = "adecco_create_start")
    private LocalDateTime adeccoCreateStart;

    @Column(name = "adecco_create_complete")
    private LocalDateTime adeccoCreateComplete;

    @Column(name = "nisra_create_start")
    private LocalDateTime nisraCreateStart;

    @Column(name = "nisra_create_complete")
    private LocalDateTime nisraCreateComplete;

    @Column(name = "actions_start")
    private LocalDateTime actionsStart;

    @Column(name = "actions_complete")
    private LocalDateTime actionsComplete;

    @Column(name = "action_type")
    private String actionType;

    @Column(name = "gsuite_action_start")
    private LocalDateTime gsuiteActionStart;

    @Column(name = "gsuite_action_complete")
    private LocalDateTime gsuiteActionComplete;

    @Column(name = "gsuite_create_start")
    private LocalDateTime gsuiteCreateStart;

    @Column(name = "gsuite_create_complete")
    private LocalDateTime gsuiteCreateComplete;

    @Column(name = "gsuite_area_group_start")
    private LocalDateTime gsuiteAreaGroupStart;

    @Column(name = "gsuite_area_group_complete")
    private LocalDateTime gsuiteAreaGroupComplete;

    @Column(name = "gsuite_coordinator_group_start")
    private LocalDateTime gsuiteCoordinatorGroupStart;

    @Column(name = "gsuite_coordinator_group_complete")
    private LocalDateTime gsuiteCoordinatorGroupComplete;

    @Column(name = "gsuite_all_user_group_start")
    private LocalDateTime gsuiteAllUserGroupStart;

    @Column(name = "gsuite_all_user_group_complete")
    private LocalDateTime gsuiteAllUserGroupComplete;

    @Column(name = "gsuite_survey_type_group_start")
    private LocalDateTime gsuiteSurveyTypeGroupStart;

    @Column(name = "gsuite_survey_type_group_complete")
    private LocalDateTime gsuiteSurveyTypeGroupComplete;

    @Column(name = "gsuite_team_drive_start")
    private LocalDateTime gsuiteTeamDriveStart;

    @Column(name = "gsuite_team_drive_complete")
    private LocalDateTime gsuiteTeamDriveComplete;

    @Column(name = "xma_start")
    private LocalDateTime xmaStart;

    @Column(name = "xma_complete")
    private LocalDateTime xmaComplete;

    @Column(name = "granby_start")
    private LocalDateTime granbyStart;

    @Column(name = "granby_complete")
    private LocalDateTime granbyComplete;

    @Column(name = "snow_start")
    private LocalDateTime snowStart;

    @Column(name = "snow_complete")
    private LocalDateTime snowComplete;

    @Column(name = "rca_start")
    private LocalDateTime rcaStart;

    @Column(name = "rca_complete")
    private LocalDateTime rcaComplete;

    @Column(name = "get_devices_start")
    private LocalDateTime DevicesStart;

    @Column(name = "get_devices_complete")
    private LocalDateTime DevicesComplete;

    @Column(name = "lws_start")
    private LocalDateTime lwsStart;

    @Column(name = "lws_complete")
    private LocalDateTime lwsComplete;
}
