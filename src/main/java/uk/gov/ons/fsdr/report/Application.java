package uk.gov.ons.fsdr.report;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"uk.gov.ons.census.fwmt.events", "uk.gov.ons.fsdr"})
public class Application {

  public static final String APPLICATION_NAME = "FSDR - Report Service";

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
