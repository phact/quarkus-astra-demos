package com.sestevez.mapper;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/creatures")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AstraDemoResource {

    @Inject
    CreatureService creatureService;

    @GET
    public List<CreatureDto> getAll(@QueryParam("name") String name) {
        return creatureService.get(name).stream()
                .map(creature -> new CreatureDto(creature.getName(), creature.getType(), creature.getAge()))
                .collect(Collectors.toList());
    }

    @POST
    public void add(CreatureDto creature) {
        creatureService.save(convertFromDto(creature));
    }

    private Creature convertFromDto(CreatureDto creatureDto) {
        return new Creature(creatureDto.getName(), creatureDto.getAge(), creatureDto.getType());
    }
}
