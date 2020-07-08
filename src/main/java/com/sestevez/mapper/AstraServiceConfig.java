package com.sestevez.mapper;

import io.quarkus.arc.config.ConfigProperties;

@ConfigProperties(prefix = "creature.dao")
public class AstraServiceConfig {
    public String getKeyspace() {
        return keyspace;
    }

    public void setKeyspace(String keyspace) {
        this.keyspace = keyspace;
    }

    String keyspace;

}
