package com.tiffanyslittlekorea.tlk.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TiffanysLittleKoreaApplication
{
  public static void main(String[] args)
  {
    SpringApplication.run(TiffanysLittleKoreaApplication.class, args);
  }
}
