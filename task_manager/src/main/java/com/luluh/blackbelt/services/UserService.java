package com.luluh.blackbelt.services;

import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.luluh.blackbelt.models.User;
import com.luluh.blackbelt.repositories.RoleRepository;
import com.luluh.blackbelt.repositories.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder)     {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    
    
    public void createUser(User user) {
    	user.setRoles(roleRepository.findByName("ROLE_USER"));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
     
 
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    public List<User> allUsers() {
        return userRepository.findAll();
    }
    
    public User findByName(String name) {
        return userRepository.findByName(name);
    }
    
}