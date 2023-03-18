package com.mdemydovych.ligazakon.mapper;

import com.mdemydovych.ligazakon.domain.Name;
import com.mdemydovych.ligazakon.model.NameDto;
import java.util.Objects;
import org.springframework.stereotype.Component;

@Component
public class NameMapper {

  public NameDto toDto(Name entity) {
    if (Objects.isNull(entity)) {
      return null;
    }
    NameDto nameDto = new NameDto();
    nameDto.setName(entity.getName());
    nameDto.setNameType(entity.getNameType());
    return nameDto;
  }


}
