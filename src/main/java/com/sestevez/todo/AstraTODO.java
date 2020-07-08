package com.sestevez.todo;

import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.quarkus.runtime.api.session.QuarkusCqlSession;
import com.sestevez.todo.API.Todo;

import javax.inject.Inject;
import javax.json.bind.annotation.JsonbProperty;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Path("/api")
public class AstraTODO {
    @Inject
    QuarkusCqlSession cqlSession;
    private String keyspaceName = "free";
    private String tableName = "todolist";

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/todo/{list_id}")
    public Response getTodos(@PathParam("list_id") String list_id) {
        PreparedStatement statement = cqlSession
                .prepare("SELECT * FROM " + keyspaceName + "." + tableName + " where list_id =?");

        BoundStatement bound = statement.bind(list_id);

        ResultSet rs = cqlSession.execute(bound);

        List<Row> rows = rs.all();
        List<Todo> todos = rows.stream().map(x -> new Todo(x.getUuid("id"), x.getString("title"), x.getBoolean("completed"))).collect(Collectors.toList());
        return Response.ok(todos).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/todo/{list_id}")
    public Response setTodo(@PathParam("list_id") String list_id, Todo todo) {
        PreparedStatement statement = cqlSession
                .prepare("INSERT INTO " + keyspaceName + "." + tableName + "(list_id, id, title, completed)VALUES(?,?,?,?)");

        BoundStatement bound = statement.bind(list_id, todo.getId(), todo.getTitle(), todo.isCompleted());

        ResultSet rs = cqlSession.execute(bound);

        boolean successful = rs.wasApplied();
        if (successful) {
            return Response.ok().build();
        }else{
            return Response.serverError().build();
        }

    }


}
