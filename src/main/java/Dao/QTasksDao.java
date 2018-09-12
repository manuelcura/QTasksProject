/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Domain.Task;
import Domain.TaskTag;
import Domain.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author manuelcura
 */
public class QTasksDao {
    
    String url = "jdbc:mysql://localhost:3306/QTasks?autoReconnect=true&useSSL=false";
    //Padrão singleton para garantir uma unica instanciação da classe
    private static QTasksDao instance;
    
    private static final Logger logger = Logger.getLogger(QTasksDao.class.getName());
    
    private QTasksDao() {}
    
    public static QTasksDao getInstance() {
        if(instance == null) {
            instance = new QTasksDao();
        }
        return instance;
    }
    
    public Task insertTaksDao(Task task, int id) {
        Timestamp timestamp = new Timestamp(task.getDate().getTime());
        int done;
        if(task.isDone()) {
            done = 1;
        }
        else {
            done = 0;
        }
        
        String query = "insert into Task (title, description, date, priority, done, tags, user_id) values "
                + "(?, ?, ?, ?, ?, ?, ?);";
        try (Connection conn = DriverManager.getConnection(url,"root","")){
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString (1, task.getTitle());
                stmt.setString (2, task.getDescription());
                stmt.setTimestamp(3, timestamp);
                stmt.setInt (4, task.getPriority());
                stmt.setInt (5, done);
                stmt.setString (6, task.getTags().name());
                stmt.setInt (7, id);
                stmt.executeUpdate();
            }
        } catch (SQLException ex) {
            logger.warning(ex.getMessage());
            return new Task();
        } 
        return task;
    }
    
    public User insertUserDao(User user) {
        String query = "insert into User (email, username, password) values "
                + "(?, ?, ?);";
        try (Connection conn = DriverManager.getConnection(url, "root", "")) {
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString (1, user.getEmail());
                stmt.setString (2, user.getUsername());
                stmt.setString (3, user.getPassword());
                stmt.executeUpdate();
            }
        } catch (SQLException ex) {
            logger.warning(ex.getMessage());
            return new User();
        }
        return user;
    }
    
    public User getUserFromDao(int id_user) {
        String query = "select id, email, username, password from User where id=?";
        User user = null;
        try (Connection conn = DriverManager.getConnection(url,"root","")){
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, id_user);
                try (ResultSet rs = stmt.executeQuery()) {
                    while(rs.next()) {
                        int id = rs.getInt("id");
                        String email = rs.getString("email");
                        String username = rs.getString("username");
                        String password = rs.getString("password");
                        user = new User(id, email, username, password);
                    }
                }
            }
        } catch (SQLException ex) {
            logger.warning(ex.getMessage());
            return new User();
        }
        return user;
    }
    
        public User getUserFromDao(String email_user) {
        String query = "select id, email, username, password from User where email=?";
        User user = null;
        try (Connection conn = DriverManager.getConnection(url,"root","")){
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, email_user);
                try (ResultSet rs = stmt.executeQuery()) {
                    while(rs.next()) {
                        int id = rs.getInt("id");
                        String email = rs.getString("email");
                        String username = rs.getString("username");
                        String password = rs.getString("password");
                        user = new User(id, email, username, password);
                    }
                }
            }
        } catch (SQLException ex) {
            logger.warning(ex.getMessage());
            return new User();
        }
        return user;
    }
    
    public Task getTaskFromDao(int id_task) {
        String query = "select id, title, description, date, priority, done, tags, user_id  from Task where id=?";
        Task task = null;
        try (Connection conn = DriverManager.getConnection(url,"root","")){
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, id_task);
                try (ResultSet rs = stmt.executeQuery()) {
                    while(rs.next()) {
                        int id = rs.getInt("id");
                        String title = rs.getString("title");
                        String description = rs.getString("description");
                        Date date = rs.getTimestamp("date");
                        int priority = rs.getInt("priority");
                        Boolean done = rs.getBoolean("done");
                        TaskTag tags = TaskTag.valueOf(rs.getString("tags"));
                        int user_id = rs.getInt("user_id");
                        task = new Task(id, title, description, date, priority, done, tags, getUserFromDao(user_id));
                    }
                }
            }
        } catch (SQLException ex) {
            logger.warning(ex.getMessage());
            return new Task();
        }
        return task;
    }
    
    public List<Task> getAllTaskFromDao(int id_user) {
        String query = "select id, title, description, date, priority, done, tags, user_id from Task where user_id=?";
        Task task = null;
        ArrayList<Task> allTasks = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url,"root","")) {
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, id_user);
                try (ResultSet rs = stmt.executeQuery()) {
                    while(rs.next()) {
                        int id = rs.getInt("id");
                        String title = rs.getString("title");
                        String description = rs.getString("description");
                        Date date = rs.getTimestamp("date");
                        int priority = rs.getInt("priority");
                        Boolean done = rs.getBoolean("done");
                        TaskTag tags = TaskTag.valueOf(rs.getString("tags"));
                        int user_id = rs.getInt("user_id");
                        task = new Task(id, title, description, date, priority, done, tags, getUserFromDao(user_id));
                        allTasks.add(task);
                    }
                }
            }
        } catch (SQLException ex) {
            logger.warning(ex.getMessage());
            return new ArrayList<>();
        }
        return allTasks;
    }
    
    public boolean deleteUserFromDao(int id_user) {
        String query_task = "delete from Task where user_id=?";
        String query_user = "delete from User where id=?";
        try (Connection conn = DriverManager.getConnection(url,"root","")){
            try (PreparedStatement stmt_task = conn.prepareStatement(query_task)) {
                stmt_task.setInt(1, id_user);
                try (PreparedStatement stmt_user = conn.prepareStatement(query_user)) {
                    stmt_task.setInt(1, id_user);
                    stmt_task.executeUpdate();
                    stmt_user.executeUpdate();
                }
            }
        }
        catch (SQLException ex) {
            logger.warning(ex.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean deleteTaskFromDao(int id_task) {
        String query = "delete from Task where id=?";
        try (Connection conn = DriverManager.getConnection(url,"root","")){
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, id_task);
                stmt.executeUpdate();
            }
        } 
        catch (SQLException ex) {
            logger.warning(ex.getMessage());
            return false;
        }
        return true;
    }
    
    public Task updateTaskFromDao(Task task, int id_task) {       
        int done;
        if(task.isDone()) {
            done = 1;
        }
        else {
            done = 0;
        } 
        String query = "update Task set title=?, description=?, priority=?, done=?"
                + ", tags=? where id=?;";
        try (Connection conn = DriverManager.getConnection(url,"root",""); PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, task.getTitle());
            stmt.setString(2, task.getDescription());
            stmt.setInt(3, task.getPriority());
            stmt.setInt(4, done);
            stmt.setString(5, task.getTags().name());
            stmt.setInt(6, id_task);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            logger.warning(ex.getMessage());
            return new Task();
        }
        return task;
    }
    
    public List<User> getAllUsersFromDao() {
        String query = "select id, email, username, password from User";
        User user = null;
        ArrayList<User> allUsers = new ArrayList<User>();

        try (Connection conn = DriverManager.getConnection(url,"root","")){
            try (PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery(query)) {
                while(rs.next()) {
                    int id = rs.getInt("id");
                    String email = rs.getString("email");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    user = new User(id, email, username, password);
                    allUsers.add(user);
                }
            }
        } catch (SQLException ex) {
            logger.warning(ex.getMessage());
            return new ArrayList<>();
        }
        return allUsers;
    }
    
    public boolean deleteUserTest(String email) {
        String query_user = "delete from User where email =?";
        try (Connection conn = DriverManager.getConnection(url,"root","")) {
            try (PreparedStatement stmt_user = conn.prepareStatement(query_user)) {
                stmt_user.setString(1, email);
                stmt_user.executeUpdate();
            }
        } catch (SQLException ex) {
            logger.warning(ex.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean deleteTaskTest(String title) {
        String query = "delete from Task where title=?";
        try (Connection conn = DriverManager.getConnection(url,"root","")){
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, title);
                stmt.executeUpdate();
            }
        } catch (SQLException ex) {
            logger.warning(ex.getMessage());
            return false;
        }
        return true;
    }
}
