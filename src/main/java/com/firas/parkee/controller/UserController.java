package com.firas.parkee.controller;

import java.util.List;

import com.firas.parkee.model.User;
import com.firas.parkee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @GetMapping
  public List<User> getAllUsers() {
    return userRepository.findAll();
  } 

  @GetMapping("/{id}")
  public User getUserById(@PathVariable Long id) {
    return userRepository.findById(id).get();
  }

  @PostMapping
  public User createUser(@ModelAttribute User user) {
    return userRepository.save(user);
  }
}
