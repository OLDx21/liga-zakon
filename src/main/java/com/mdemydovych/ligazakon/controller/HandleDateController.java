package com.mdemydovych.ligazakon.controller;

import com.mdemydovych.ligazakon.service.HandleDateService;
import io.swagger.v3.oas.annotations.Operation;
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
  @Operation(description = "End point which takes an html file as input,"
      + " and returns the same file, with selected dates")
  public String handleDates(MultipartFile file) {
    return handleDateService.markDates(file);
  }

}
