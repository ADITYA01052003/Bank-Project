package in.bank.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.bank.main.entity.Account;
import in.bank.main.entity.User;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
	 //Account findByUser(User user);
	//Account findByUserId(Long userId);
	Optional<Account> findByUser(User user);
}
