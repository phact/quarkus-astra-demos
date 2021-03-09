package com.sestevez.todo;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.quarkus.runtime.api.session.QuarkusCqlSession;
import com.sestevez.todo.api.Todo;
import io.quarkus.runtime.StartupEvent;
import io.smallrye.common.annotation.Blocking;

@ApplicationScoped
@Path("/api")
public class AstraTODO {
    @Inject
    QuarkusCqlSession cqlSession;

    private String keyspaceName = "free";
    private String tableName = "todolist";

    public boolean onStart(@Observes StartupEvent ev){
        ResultSet rs = this.cqlSession.execute("CREATE TABLE IF NOT EXISTS " + this.keyspaceName+"." + this.tableName + "(id text PRIMARY KEY, title text, completed boolean);");
        System.out.println("**** Table created " + rs.wasApplied() + "****");
        return rs.wasApplied();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/todo/{list_id}")
    @Blocking
    public List<Todo> getTodos(@PathParam("list_id") String list_id) {
        PreparedStatement statement = this.cqlSession
                .prepare("SELECT * FROM " + this.keyspaceName + "." + this.tableName + " where list_id =?");

        BoundStatement bound = statement.bind(list_id);

        ResultSet rs = this.cqlSession.execute(bound);

        List<Row> rows = rs.all();
        return rows.stream()
          .map(x -> new Todo(x.getUuid("id"), x.getString("title"), x.getBoolean("completed")))
          .collect(Collectors.toList());
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/todo/{list_id}")
    @Blocking
    public Response setTodo(@PathParam("list_id") String list_id, Todo todo) {
        PreparedStatement statement = this.cqlSession
                .prepare("INSERT INTO " + this.keyspaceName + "." + this.tableName + "(list_id, id, title, completed)VALUES(?,?,?,?)");

        BoundStatement bound = statement.bind(list_id, todo.getId(), todo.getTitle(), todo.isCompleted());

        ResultSet rs = this.cqlSession.execute(bound);

        boolean successful = rs.wasApplied();
        if (successful) {
            return Response.ok().build();
        }else{
            return Response.serverError().build();
        }

    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/todo/{list_id}/{id}")
    @Blocking
    public boolean getTodos(@PathParam("list_id") String list_id, @PathParam("id") String id){
        PreparedStatement statement = this.cqlSession.prepare("DELETE FROM " + this.keyspaceName + "." +
                this.tableName + " WHERE list_id = ? AND id = ?");

        BoundStatement boundStmt = statement.bind(list_id, id);

        ResultSet rs = this.cqlSession.execute(boundStmt);

        return rs.wasApplied();

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/todo/{list_id}/{id}")
    @Blocking
    public boolean completeTodo(@PathParam("list_id") String list_id, @PathParam("id") String id) {

        PreparedStatement stmt = this.cqlSession.prepare("INSERT INTO " + this.keyspaceName +
                "." + this.tableName + " (id, list_id, completed) VALUES (?, ?, ?)");

        BoundStatement boundStmt = stmt.bind(id, list_id, true);

        ResultSet rs = this.cqlSession.execute(boundStmt);

        return rs.wasApplied();

    }



}
