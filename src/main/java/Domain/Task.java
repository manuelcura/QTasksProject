/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author manuelcura
 */

@Entity
@Table(name = "Task")
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Size(max = 64)
    @NotNull
    @Basic(optional = false)
    @Column(name = "title")
    private String title;
    
    @Size(max = 300)
    @Column(name = "description")
    private String description;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "priority")
    private int priority;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "done")
    private boolean done;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "tags")
    private TaskTag tags;
    
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private User userId;
    
    public Task() {
    }
    
    public Task(int id, String title, String description, Date date, int priority, boolean done, TaskTag tags, User user_id) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.priority = priority;
        this.done = done;
        this.tags = tags;
        this.userId = user_id;
    }
    
        public Task(String title, String description, Date date, int priority, boolean done, TaskTag tags, User user_id) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.priority = priority;
        this.done = done;
        this.tags = tags;
        this.userId = user_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public TaskTag getTags() {
        return tags;
    }

    public void setTags(TaskTag tags) {
        this.tags = tags;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Task{" + "id=" + id + ", title=" + title + ", description=" + description + ", date=" + date + ", priority=" + priority + ", done=" + done + ", tags=" + tags + ", userId=" + userId + '}';
    }
    
}
