package com.luluh.blackbelt.services;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.luluh.blackbelt.models.Task;
import com.luluh.blackbelt.repositories.TaskRepository;



@Service
public class TaskService {
    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }
    
    public void createTask(Task task) {
    	taskRepository.save(task);
    }
    
    public List<Task> allTasks() {
        return taskRepository.findAll();
    }
    
    public Task findTask(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if(optionalTask.isPresent()) {
            return optionalTask.get();
        } else {
            return null;
        }
    }
    
	public @Valid Task updateTask(@Valid Task task) {
		Optional<Task> optionalTask = taskRepository.findById(task.getId());
		  if(optionalTask.isPresent()) {
			  Task optionalTask1 = optionalTask.get();
			  optionalTask1.setTask1(task.getTask1());
			  optionalTask1.setAssignee(task.getAssignee());
			  optionalTask1.setPriority(task.getPriority());
			  return taskRepository.save(optionalTask1);   
	        } else {
	            return null;
	        }
	}
	
	public void deleteTask(Long id) {
		taskRepository.deleteById(id);
	}
	
	public List<Task> findByPriority(String p){
		return taskRepository.findByPriority(p);
	}
	
	public List<Task> findByAssignee(String a){
		return taskRepository.findByAssignee(a);
	}
}
