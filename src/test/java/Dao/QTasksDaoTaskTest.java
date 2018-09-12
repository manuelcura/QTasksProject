/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Domain.Task;
import Domain.TaskTag;
import Domain.User;
import java.sql.SQLException;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
/**
 *
 * @author manuelcura
 */
public class QTasksDaoTaskTest {
    
    public QTasksDaoTaskTest() {
    }
    
    Task task;
    QTasksDao dao;
    
    @Before
    public void setUpUser() {
        dao = QTasksDao.getInstance();
    }



    @Test
    public void testInsertTaskDao() throws SQLException {
        System.out.println("insertTaskDao");
        User user = dao.getUserFromDao(1);
        task = new Task("Teste", "Testar", new Date(), 3, true, TaskTag.Other, user);
        Task result = dao.insertTaksDao(task, user.getId());
        
        assertEquals(task.getTitle(), result.getTitle());
        assertEquals(task.getDescription(), result.getDescription());
        assertEquals(task.getDate(), result.getDate());
        assertEquals(task.getTags(), result.getTags());
        cleanDB();
    }

    @Test
    public void testGetTaskFromDao() {
        System.out.println("getTaskFromDao");
        User user = dao.getUserFromDao(1);
        task = new Task("Task teste 2", "So para testar 2", new Date(), 3, true, TaskTag.Health, user);
        Task result = dao.getTaskFromDao(3);
        
        assertEquals(task.getTitle(), result.getTitle());
        assertEquals(task.getTags(), result.getTags());
        assertEquals(task.getPriority(), result.getPriority());        
    }

    @Test
    public void testDeleteTaskFromDao() {
        System.out.println("deleteTaskFromDao");
        User user = dao.getUserFromDao(1);
        task = new Task("Teste", "Testar", new Date(), 3, true, TaskTag.Other, user);
        dao.insertTaksDao(task, user.getId());
        boolean result = dao.deleteTaskTest(task.getTitle());
        assertTrue(result);
    }

    @Test
    public void testUpdateTaskFromDao() {
        System.out.println("updateTaskFromDao");
        User user = dao.getUserFromDao(1);
        task = new Task("Task teste 2", "So para testar 2", new Date(), 3, true, TaskTag.Health, user);
        //Fazer update de uma task já existente na BD
        Task result = dao.updateTaskFromDao(task, 3);  
        assertEquals(task.getTitle(), result.getTitle());
    }
    
    private void cleanDB() {
        //Depois de testado é necessário realizar a limpeza das alterações na base de dados
        //Porque o rollback das alterações não foi possível na minha implementação do dao
        dao.deleteTaskTest("Teste");
    }
}
