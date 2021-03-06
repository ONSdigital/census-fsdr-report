package uk.gov.ons.fsdr.report.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;
import uk.gov.ons.fsdr.report.repository.ReportRepository;
import uk.gov.ons.fsdr.report.service.CsvService;

import java.io.IOException;

@Controller
@RequestMapping("/report")
public class ReportController {

  @Autowired
  private ReportRepository reportRepository;

  @Autowired
  private CsvService csvService;

  @GetMapping("/clearDB")
  public ResponseEntity<HttpStatus> clearDatabase() {
    reportRepository.deleteAll();
    return ResponseEntity.ok(HttpStatus.OK);
  }

  @GetMapping(value = "/csv", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
  public @ResponseBody byte[] getCsv() {
    String csv;
    try {
      csv = csvService.buildCsv();
      return csv.getBytes();
    } catch (IOException e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

}
