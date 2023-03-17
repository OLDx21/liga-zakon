package com.mdemydovych.ligazakon.controller;

import com.mdemydovych.ligazakon.document.Person;
import com.mdemydovych.ligazakon.service.HandlePersonService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/person")
@RequiredArgsConstructor
public class HandlePersonController {

  private final HandlePersonService handlePersonService;

  @GetMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public List<Person> handlePeople(MultipartFile file) {
    return handlePersonService.getPersonList(file);
  }

}
