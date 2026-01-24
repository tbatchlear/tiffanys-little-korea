package com.tiffanyslittlekorea.tlk.web.controller;

import com.tiffanyslittlekorea.tlk.domain.PingResponse;
import com.tiffanyslittlekorea.tlk.service.PingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController
{
  private final PingService pingService;

  public PingController(PingService pingService)
  {
    this.pingService = pingService;
  }

  @GetMapping("/api/ping")
  public PingResponse ping()
  {
    return pingService.ping();
  }
}
