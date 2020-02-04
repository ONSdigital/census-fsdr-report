package uk.gov.ons.fsdr.report;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan({"uk.gov.ons.census.fwmt.events", "uk.gov.ons.fsdr"})
public class FsdrReportApplication {

	public static void main(String[] args) {
		SpringApplication.run(FsdrReportApplication.class, args);
	}

}
