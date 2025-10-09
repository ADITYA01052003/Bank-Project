package in.bank.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.bank.main.dto.LoginRequest;
import in.bank.main.entity.User;
import in.bank.main.repository.UserRepository;
import in.bank.main.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepo;
	
	@PostMapping("/login")
	public User Login(@RequestBody LoginRequest request){
		User user = userRepo.findByEmail(request.getEmail());
		if(user != null && user.getPassword().equals(request.getPassword())) {
			return user;
		}
		throw new RuntimeException("Invalid Credentials");
		
	}

}
