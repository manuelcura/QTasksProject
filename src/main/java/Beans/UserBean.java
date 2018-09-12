/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Dao.QTasksDao;
import Domain.User;
import java.util.List;

/**
 *
 * @author manuelcura
 */
public class UserBean {
    
    //Padrão singleton para garantir uma unica instanciação da classe
    private static UserBean instance;
    private QTasksDao dao = QTasksDao.getInstance();
    
    private UserBean() {}
    
    public static UserBean getInstance() {
        if(instance == null) {
            instance = new UserBean();
        }
        return instance;
    }
    
    public User getUser(int id) {
        return dao.getUserFromDao(id);
    }
    
    public User createUser(User user) {
        String[] split = user.getEmail().split("@");
        String username = split[0];
        user.setUsername(username);
        return dao.insertUserDao(user);
    }
    
    public boolean deleteUser(int id) {
        return dao.deleteUserFromDao(id);
    }
    
    public User validateUser(User user) {
        List<User> allUsers = dao.getAllUsersFromDao();
        for(User u: allUsers) {
            if((u.getEmail().equals(user.getEmail())) && (u.getPassword().equals(user.getPassword()))) {
                return u;
            }
        }
        return new User();
    }
}
