package in.bank.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.bank.main.service.UserService;
import in.bank.main.dto.LoginRequest;
import in.bank.main.entity.User;
import in.bank.main.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired 
	private UserService userService;
	
	@PostMapping("/register")
	public User registerUser(@RequestBody User user){
		return userService.registerUser(user);
	}
	
	@GetMapping("/{userId}")
	public User getUser(@PathVariable Long userId) {
		return userService.getUserById(userId);
	}
	
	@GetMapping("/all")
	public  List<User>getAllUser(){
		return userService.getAllUsers();	
	}
	
	@DeleteMapping("/delete/{userId}")
	public String deleteUser(@PathVariable Long userId) {
		userService.deleteUser(userId);
		return "User deleted successfully";
	}
	

}
