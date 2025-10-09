package in.bank.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.bank.main.entity.Account;
import in.bank.main.entity.User;
import in.bank.main.repository.AccountRepository;
import in.bank.main.repository.UserRepository;
import in.sp.main.entites.AccountStatus;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountRepository repo;
	
	@Autowired 
	private UserRepository userRepo;
	
	@Override
	public Account createAccount(Account account, Long userId) {
	    User user = userRepo.findById(userId)
	        .orElseThrow(() -> new RuntimeException("User not found"));

	    account.setUser(user); // link account to user
	    return repo.save(account); // user_id will now be saved in DB
	}


	@Override
	public Account getAccountDetailsByAccountNumber(Long accountNumber) {
		Optional<Account>account = repo.findById(accountNumber);
		if(account.isEmpty()) {
			throw new RuntimeException("Account is not existed");
		}
		Account account_found = account.get();
		Long userId = account.get().getUserId(); // <-- here you can access userId
	    System.out.println("User ID linked to account: " + userId);
	    
		return null;
	}

	@Override
	public List<Account> getAllAccountDetails() {
		List<Account>ListOfAccount = repo.findAll();
		return ListOfAccount;
	}

	@Override
	public Account depositeAmount(Long accountNumber, Double amount) {
		Optional<Account>account = repo.findById(accountNumber);
		if(account.isEmpty()) {
			throw new RuntimeException("Account is not existed");
		}
		Account accountPresent = account.get();
		Double totalBalance = accountPresent.getAccount_balance() + amount;
		accountPresent.setAccount_balance(totalBalance);
		repo.save(accountPresent);
		return accountPresent;
	}

	@Override
	public Account withdrawAccount(Long accountNumber, Double amount) {
		Optional<Account>account = repo.findById(accountNumber);
		 
		if(account.isEmpty()) {
			throw new RuntimeException("Account is not existed");
		}
		Account accountPresent = account.get();
		Double accountBalance = accountPresent.getAccount_balance() - amount;
		accountPresent.setAccount_balance(accountBalance);
		repo.save(accountPresent);
		return accountPresent;
	}

	@Override
	public void closeAccount(Long accountNumber) {
		getAccountDetailsByAccountNumber(accountNumber);
		Account account = new Account();
		repo.deleteById(accountNumber);
	}

}
