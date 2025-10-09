package in.bank.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.bank.main.entity.User;
import in.bank.main.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public User registerUser(User user) {
		return userRepo.save(user);
	}

	@Override
	public User getUserById(Long userId) {
		Optional<User> user = userRepo.findById(userId);
		
		if(user.isEmpty()) {
			throw new RuntimeException("User not found");
		}
		return user.get();
	}

	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public void deleteUser(Long userId) {
		userRepo.deleteById(userId);
	}

}
