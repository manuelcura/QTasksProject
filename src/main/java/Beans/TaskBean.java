/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Dao.QTasksDao;
import Domain.Task;
import java.util.List;

/**
 *
 * @author manuelcura
 */
public class TaskBean {
    private static TaskBean instance;
    private QTasksDao dao = QTasksDao.getInstance();
    
    private TaskBean() {}
    
    public static TaskBean getInstance() {
        if(instance == null) {
            instance = new TaskBean();
        }
        return instance;
    }
    
    public Task getTask(int id) {
        return dao.getTaskFromDao(id);
        
    }
    
    public Task createTask(Task task, int id) {
        return dao.insertTaksDao(task, id);
    }
    
    public boolean deleteTask(int id) {
        return dao.deleteTaskFromDao(id);
    } 
    
    public Task updateTask(Task task, int id) {
        return dao.updateTaskFromDao(task, id);
    }
    
    public List<Task> getAllTask(int id) {
        
        return dao.getAllTaskFromDao(id);
    }
}
