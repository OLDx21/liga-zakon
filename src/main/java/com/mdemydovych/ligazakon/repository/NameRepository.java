package com.mdemydovych.ligazakon.repository;

import com.mdemydovych.ligazakon.domain.Name;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NameRepository extends JpaRepository<Name, String> {

  Optional<Name> findByNameIgnoreCase(String name);
}
