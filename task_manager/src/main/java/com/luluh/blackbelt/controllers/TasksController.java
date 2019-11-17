package com.luluh.blackbelt.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.luluh.blackbelt.models.Task;
import com.luluh.blackbelt.models.User;
import com.luluh.blackbelt.services.TaskService;
import com.luluh.blackbelt.services.UserService;
import com.luluh.blackbelt.validator.UserValidator;


@Controller
public class TasksController {
	
    private UserService userService;
    private TaskService taskService;
    private UserValidator userValidator;
    private static ArrayList<String> priorities;

    public TasksController(UserService userService,  TaskService taskService, UserValidator userValidator) {
        this.userService = userService;
        this.taskService = taskService;
        this.userValidator = userValidator;
        priorities = new ArrayList<>();
        priorities.add("High");
        priorities.add("Medium");
        priorities.add("Low");
    }

    
    //login and registration page
    @RequestMapping("/login")
    public String login(@Valid @ModelAttribute("user") User user, @RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, Model model) {
        if(error != null) {
            model.addAttribute("errorMessage", "Invalid Credentials, Please try again.");
        }
        if(logout != null) {
            model.addAttribute("logoutMessage", "Logout Successful!");
        }
        return "index.jsp";
    }
    
    //function to register a new user
    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return "index.jsp";
        }
        else {
	        userService.createUser(user);
	        return "redirect:/";
        }
    }
    
    //function to display current tasks
	@RequestMapping("/")
    public String redirect() {
		return "redirect:/tasks";
    }
   
	//function to display current tasks
	@RequestMapping("/tasks")
    public String homePage(Principal principal, Model model) {
        String username = principal.getName();
        model.addAttribute("currentUser", userService.findByUsername(username));
        //1. BLACKBET: sort from high to low, and low to high
        List<Task> tasksH = taskService.findByPriority("High");        
        List<Task> tasksL = taskService.findByPriority("Low"); 
        List<Task> tasksM = taskService.findByPriority("Medium"); 
        model.addAttribute("tasksH", tasksH);
        model.addAttribute("tasksL", tasksL);
        model.addAttribute("tasksM", tasksM);
        return "homePage.jsp";
    }
	
	//function to create new task
    @RequestMapping("/tasks/new")
    public String newTask(@ModelAttribute("task") Task task, Model model, Principal principal) {
        String username = principal.getName();
        model.addAttribute("currentUser", userService.findByUsername(username));
	   	List<User> users = userService.allUsers();
	   	model.addAttribute("users", users);
    	model.addAttribute("priorities", priorities);
        return "/new_task.jsp";
    }
    
    //function to create new task
    @PostMapping(value="/tasks/new")
    public String createTasks(@Valid @ModelAttribute("task") Task task, Model model, BindingResult result) {
        if (result.hasErrors()) {
            return "/new_task.jsp";
        } else {
        	
        	//2.BLACKBELT: Do not let a particular user to be assigned more than 3 tasks.
        	List<Task> t = taskService.findByAssignee(task.getAssignee());
        	if(t.size() < 3) {
        		taskService.createTask(task);
        		return "redirect:/";
        	}
        	else {
        		String a = "User already assigned three tasks";
        	   	List<User> users = userService.allUsers();
        	   	model.addAttribute("users", users);
            	model.addAttribute("priorities", priorities);
        		model.addAttribute("a",a);
        		return "/new_task.jsp";
        	}
        }
    }
    
    //function to display a particular task
    @RequestMapping("/tasks/{id}")
    public String showTask(@PathVariable("id") Long id, Model model, Principal principal) {
        String username = principal.getName();
        User currentUser = userService.findByUsername(username);
        model.addAttribute("currentUser", currentUser);
        Task task = taskService.findTask(id);
        String creator = task.getCreator();
        String assignee = task.getAssignee();
        String user_name = currentUser.getName();
        model.addAttribute("task", task);
        boolean display = false;
        boolean show = false;
        if(user_name.equals(creator)) {
        	//3a. BLACKBELT: only display edit and delete for the creator is the same user logged in.
        	display = true;
        	model.addAttribute("display", display);
        	
        }
        if(user_name.equals(assignee)){
        	//4. BLACKBELT: The completed button is displayed only if the the assignee is the same user logged in.
        	show = true;
        	model.addAttribute("show", show);        	
        }
  
        model.addAttribute("show", show);
        model.addAttribute("dsiplay", display);
        return "/show_task.jsp";       
    }
    
    //function to edit a particular task
    @RequestMapping("/tasks/{id}/edit")
    public String editTask(@PathVariable("id") Long id, Model model,Principal principal) {
        Task task = taskService.findTask(id);
        model.addAttribute("task", task);
	   	List<User> users = userService.allUsers();
	   	model.addAttribute("users", users);
    	model.addAttribute("priorities", priorities);
		String username = principal.getName();
        User currentUser = userService.findByUsername(username);
        model.addAttribute("currentUser", currentUser);
        String creator = task.getCreator();
        String user_name = currentUser.getName();
        
        //3b. BLACKBELT: only the creator of the task can display the edit page, if not they'll be redirect to '/tasks'.
        if(user_name.equals(creator)) {
            
        	return "/edit_task.jsp";
        }
        return "redirect:/tasks";
    }
            
    //function to edit a particular task
    @PostMapping("/tasks/{id}/edit")
    public String updateTask(@Valid @ModelAttribute("task") Task task, Principal principal, Model model, BindingResult result) {
        if (result.hasErrors()) {
            return "/edit_task.jsp";
        } else {
            	taskService.updateTask(task);           
            	return "redirect:/";
        }
    }
    
    //function to remove a particular task
    @RequestMapping(value="/tasks/{id}/remove")
    public String removeTask(@PathVariable("id") Long id) {
    	taskService.deleteTask(id);
        return "redirect:/";
    }

     
}





