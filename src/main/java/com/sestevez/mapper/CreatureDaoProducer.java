package com.sestevez.mapper;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.quarkus.runtime.api.session.QuarkusCqlSession;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

public class CreatureDaoProducer {

    private final CreatureDao creatureDao;

    @Inject
    public CreatureDaoProducer(QuarkusCqlSession cqlSession) {
        // create a mapper
        CreatureMapper mapper = new CreatureMapperBuilder(cqlSession).build();
        // instantiate our Daos
        creatureDao = mapper.creatureDao();
    }

    @Produces
    @ApplicationScoped
    CreatureDao produceCreatureDao() {
        return creatureDao;
    }
}

