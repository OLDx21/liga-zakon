package com.mdemydovych.ligazakon.service;

import com.mdemydovych.ligazakon.document.Person;
import com.mdemydovych.ligazakon.model.NameDto;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class HandlePersonService {

  private final NameService nameService;

  @Value("${gender.regular.expression}")
  private String regex;

  private final Set<String> cacheNoNames = new HashSet<>();

  public List<Person> getPersonList(MultipartFile file) {
    List<String> lines = getLines(file);
    return extractPersonsData(lines);
  }

  private List<Person> extractPersonsData(List<String> lines) {
    return lines.parallelStream()
        .map(this::extractPerson)
        .filter(this::isEmptyFields)
        .collect(Collectors.toList());
  }

  private Person extractPerson(String line) {
    final var person = new Person();
    String[] words = line.split(" ");
    for (String word : words) {
      if (cacheNoNames.contains(word.toLowerCase())) {
        continue;
      }
      Optional<NameDto> name = nameService.isNameContains(word);
      name.ifPresent(nameDto -> {
        switch (nameDto.getNameType()) {
          case FIRST_NAME -> person.setName(nameDto.getName());
          case LAST_NAME -> person.setSurname(nameDto.getName());
        }
      });
      if (name.isEmpty()) {
        cacheNoNames.add(word.toLowerCase());
      }
    }
    person.setPersonType(getPersonGender(line));
    return person;
  }

  private String getPersonGender(String line) {
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(line.toLowerCase());
    return matcher.find() ? matcher.group() : "";
  }

  private boolean isEmptyFields(Person person) {
    return !(Objects.isNull(person.getName())
        && Objects.isNull(person.getPersonType())
        && Objects.isNull(person.getSurname()));
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
