/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Domain.User;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author manuelcura
 */
public class QTasksDaoUserTest {
    
    QTasksDao dao;
    User user;
    
    @Before
    public void setUpUser() {
        dao = QTasksDao.getInstance();
        user = new User("usertest@ua.pt", "usertest", "1234");
    }
    
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        QTasksDao expResult = QTasksDao.getInstance();
        QTasksDao result = QTasksDao.getInstance();
        assertEquals(expResult.hashCode(), result.hashCode());
    }
    
    @Test
    public void testInsertUserDao() {
        System.out.println("insertUserDao");
        User result = dao.insertUserDao(user);
        assertEquals(user.getEmail(), result.getEmail());
        cleanDB();
    }
    
    @Test
    public void testGetUserFromDao() {
        System.out.println("getUserFromDao");
        User result = dao.getUserFromDao(1);
        String email = "manuelcura@ua.pt";
        assertEquals(email, result.getEmail());
    }
    
    @Test
    public void testDeleteUserFromDao() {
        System.out.println("deleteUserFromDao");
        dao.insertUserDao(user);
        boolean result = dao.deleteUserTest(user.getEmail());
        assertTrue(result);
    }
    
    @Test
    public void testFalseDeleteUserFromDao() {
        System.out.println("falseDeleteUserFromDao");
        boolean result = dao.deleteUserFromDao(0);
        assertFalse(result);
    }
    
    private void cleanDB() {
        dao.deleteUserTest(user.getEmail());
    }
}
