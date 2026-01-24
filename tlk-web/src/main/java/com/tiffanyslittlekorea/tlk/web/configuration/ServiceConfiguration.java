package com.tiffanyslittlekorea.tlk.web.configuration;

import com.tiffanyslittlekorea.tlk.service.PingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration
{
  @Bean
  public PingService pingService()
  {
    return new PingService();
  }
}
