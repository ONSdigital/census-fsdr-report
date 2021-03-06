package uk.gov.ons.fsdr.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.gov.ons.fsdr.report.entity.Report;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, String> {
  List<Report> findAllByUniqueEmployeeIdNot(String id);
}
