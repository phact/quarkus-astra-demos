package com.sestevez.mapper;

import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Select;
import com.datastax.oss.driver.api.mapper.annotations.Update;

@Dao
public interface CreatureDao{

    @Update
    void update(Creature creature);

    @Select
    PagingIterable<Creature> findById(String name);

}
