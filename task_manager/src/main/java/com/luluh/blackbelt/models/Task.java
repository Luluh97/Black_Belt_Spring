package com.luluh.blackbelt.models;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name="tasks")
public class Task {
    
    @Id
    @GeneratedValue
    private Long id;
    

    private String task1;
    

    private String assignee;

    private String priority;
    private String creator;
    
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;

    @ManyToOne( targetEntity = User.class,fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
    
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    
    public Task() {
    }
    
    public Task( Long id, String task1, String assignee, String priority, Date createdAt, Date updatedAt) {
    	this.id = id;
    	this.task1 = task1;
    	this.priority = priority;
    	this.createdAt = createdAt;
    	this.updatedAt = updatedAt;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    
    public String getTask1() {
        return task1;
    }
    public void setTask1(String task1) {
        this.task1 = task1;
    }
    
    
    public String getAssignee() {
        return assignee;
    }
    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }
    
    
    public String getPriority() {
        return priority;
    }
    public void setPriority(String priority) {
        this.priority = priority;
    }
    
    
    public String getCreator() {
        return creator;
    }
    public void setCreator(String creator) {
        this.creator = creator;
    }
    
    
    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    
    
    public Date getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    
    
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
}