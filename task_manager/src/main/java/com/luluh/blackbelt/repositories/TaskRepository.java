package com.luluh.blackbelt.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.luluh.blackbelt.models.Task;


public interface TaskRepository extends CrudRepository<Task, Long>{
	
	List<Task> findAll();
	List<Task> findByPriority(String p);
	List<Task> findByAssignee(String a);
	

}
