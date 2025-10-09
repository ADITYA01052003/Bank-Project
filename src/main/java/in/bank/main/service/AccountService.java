package in.bank.main.service;

import java.util.List;

import in.bank.main.entity.Account;
import in.bank.main.entity.User;

public interface AccountService {
	
	public Account createAccount(Account account, Long userId);
	public Account getAccountDetailsByAccountNumber(Long accountNumber);
	public List<Account>getAllAccountDetails();
	public Account depositeAmount(Long accountNumber,Double amout);
	public Account withdrawAccount(Long accountNumber,Double amount);
	public void closeAccount(Long accountNumber);

}
