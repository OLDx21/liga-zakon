package com.mdemydovych.ligazakon.service;

import com.mdemydovych.ligazakon.mapper.NameMapper;
import com.mdemydovych.ligazakon.model.NameDto;
import com.mdemydovych.ligazakon.repository.NameRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NameService {

  private final NameRepository nameRepository;

  private final NameMapper nameMapper;

  private final Map<String, NameDto> cache = new HashMap<>();

  public Optional<NameDto> isNameContains(String name) {
    if (cache.containsKey(name.toLowerCase())) {
      return Optional.ofNullable(cache.get(name.toLowerCase()));
    }
    return nameRepository.findByNameIgnoreCase(name).map(entity -> {
      NameDto nameDto = nameMapper.toDto(entity);
      cache.put(entity.getName().toLowerCase(), nameDto);
      return nameDto;
    });
  }

}
