package com.mdemydovych.ligazakon.controller;

import com.mdemydovych.ligazakon.service.HandleDateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/date")
@RequiredArgsConstructor
public class HandleDateController {

  private final HandleDateService handleDateService;

  @GetMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public String handleDates(MultipartFile file) {
    return handleDateService.markDates(file);
  }

}
