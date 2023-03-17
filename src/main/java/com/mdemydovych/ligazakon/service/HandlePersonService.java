package com.mdemydovych.ligazakon.service;

import com.mdemydovych.ligazakon.document.Person;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class HandlePersonService {

  public List<Person> getPersonList(MultipartFile file) {
    List<String> lines = getLines(file);
    return null;
  }

  @SneakyThrows
  private List<String> getLines(MultipartFile file) {
    BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
    List<String> lines = new ArrayList<>();
    while (reader.ready()) {
      lines.add(reader.readLine());
    }
    return lines;
  }

}
