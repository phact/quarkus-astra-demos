package com.sestevez.mapper;

import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;

@Mapper
public interface CreatureMapper {
    @DaoFactory
    CreatureDao creatureDao();
}
