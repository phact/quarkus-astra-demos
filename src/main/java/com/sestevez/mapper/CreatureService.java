package com.sestevez.mapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class CreatureService {

    private final CreatureDao dao;

    @Inject
    public CreatureService(CreatureDao dao) {
        this.dao = dao;
    }

    public void save(Creature creature) {
        dao.update(creature);
    }

    public List<Creature> get(String id) {
        return dao.findById(id).all();
    }
}
