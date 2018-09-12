/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Beans.UserBean;
import Domain.User;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author manuelcura
 */
@Stateless
@Path("user")
public class UserController {
    
    private final UserBean userBean = UserBean.getInstance();
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public User findUser(@PathParam("id") Integer id) {
        return userBean.getUser(id);
    }
    
    @POST
    @Path("/login")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public User validateUser(User user) {
        return userBean.validateUser(user);
    }
    
    @POST
    @Path("/register")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public User registerUser(User user) {
        return userBean.createUser(user);
    }

    @DELETE
    @Path("{id}")
    public boolean removeUser(@PathParam("id") Integer id) {
        return userBean.deleteUser(id);
    }
}
