package in.bank.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.bank.main.entity.Account;
import in.bank.main.repository.AccountRepository;
import in.bank.main.service.AccountService;
import in.sp.main.entites.AccountStatus;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountService service;
	
	@Autowired
	private AccountRepository accRepo;
	//create the account
	@PostMapping("/create")
	public Account createAccount(@RequestBody Account account, @RequestParam Long userId) {
		return service.createAccount(account, userId);
	}
	
	@GetMapping("/{accountNumber}")
	public Account getAccountDetailsByAccountNumber(@PathVariable Long accountNumber){
		//Account account = service.getAccountDetailsByAccountNumber(accountNumber);
		return service.getAccountDetailsByAccountNumber(accountNumber);
		
	}
	
	@GetMapping("/getAllAccounts")
	public List<Account>getAllAccountDetails(){
		List<Account>allAccountDetails = service.getAllAccountDetails();
		return allAccountDetails;
	}
	
	@PutMapping("/deposite/{accountNumber}/{amount}")
	public Account depositeAccount(@PathVariable Long accountNumber, @PathVariable Double amount) {
		return service.depositeAmount(accountNumber, amount);
	}
	
	@PutMapping("/withdraw/{accountNumber}/{amount}")
	public Account withdrawAccount(@PathVariable Long accountNumber, @PathVariable Double amount) {
		return service.withdrawAccount(accountNumber, amount);
	}
	
	@DeleteMapping("/delete/{accountNumber}")
	public ResponseEntity<String> deleteAccount(@PathVariable Long accountNumber) {
		service.closeAccount(accountNumber);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Account closed");
	}
}
