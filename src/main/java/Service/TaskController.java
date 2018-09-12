/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Beans.TaskBean;
import Domain.Task;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author manuelcura
 */
@Stateless
@Path("task")
public class TaskController {
    
    private final TaskBean taskBean = TaskBean.getInstance();
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Task findTask(@PathParam("id") Integer id) {
        return taskBean.getTask(id);
    }
    
    @GET
    @Path("all/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Task> findAllTask(@PathParam("id") Integer id) {
        return taskBean.getAllTask(id);
    }
    
    @POST
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Task createTask(@PathParam("id") Integer id, Task task) {
        return taskBean.createTask(task, id);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Task editTask(@PathParam("id") Integer id, Task task) {
        return taskBean.updateTask(task, id);
    }

    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public boolean removeTask(@PathParam("id") Integer id) {
        return taskBean.deleteTask(id);
    }
}
