package com.tiffanyslittlekorea.tlk.service;


import com.tiffanyslittlekorea.tlk.domain.PingResponse;

public class PingService
{
  public PingResponse ping()
  {
    return new PingResponse("pong");
  }
}
