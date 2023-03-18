package com.mdemydovych.ligazakon.model;

import com.mdemydovych.ligazakon.domain.NameType;
import lombok.Data;

@Data
public class NameDto {

  private String name;

  private NameType nameType;

}
