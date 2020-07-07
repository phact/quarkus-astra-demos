package com.sestevez.cql;

import com.datastax.oss.driver.api.core.cql.AsyncResultSet;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.quarkus.runtime.api.session.QuarkusCqlSession;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

@Path("/hello")
public class AstraDemoCQL {

    @Inject QuarkusCqlSession cqlSession;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        ResultSet rs = cqlSession
                .execute("SELECT data_center FROM system.local");

        Row row = rs.one();
        String version = row.getFormattedContents();
        return "hello " + version;
    }


}