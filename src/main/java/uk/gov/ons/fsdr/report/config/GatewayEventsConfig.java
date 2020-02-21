package uk.gov.ons.fsdr.report.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.gov.ons.census.fwmt.events.component.GatewayEventManager;
import uk.gov.ons.fsdr.report.Application;

@Configuration
public class GatewayEventsConfig {

  @Bean
  public GatewayEventManager gatewayEventManager() {
    GatewayEventManager gatewayEventManager = new GatewayEventManager();
    gatewayEventManager.setSource(Application.APPLICATION_NAME);
    gatewayEventManager.addErrorEventTypes(new String[] {});
    gatewayEventManager.addEventTypes(new String[] {});
    return gatewayEventManager;
  }
}
