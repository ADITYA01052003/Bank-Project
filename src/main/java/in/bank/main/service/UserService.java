package in.bank.main.service;

import java.util.List;

import in.bank.main.entity.User;

public interface UserService {
	User registerUser(User user);
	User getUserById(Long userId);
	List<User> getAllUsers();
	void deleteUser(Long userId);

}
