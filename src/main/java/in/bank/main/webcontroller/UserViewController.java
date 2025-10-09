package in.bank.main.webcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.bank.main.dto.LoginRequest;
import in.bank.main.entity.User;
import in.bank.main.entity.Account;
import in.bank.main.repository.AccountRepository;
import in.bank.main.repository.UserRepository;
import in.bank.main.service.UserService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/bank")
public class UserViewController {
	@Autowired 
	private UserService userService;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AccountRepository accRepo;

	@GetMapping("/user-pages")
	public String usersPage(Model model) {
		model.addAttribute("users", userService.getAllUsers());
		return "users";// maps to templates/users.html
	}
	
	@PostMapping("/register")
	public String registerUser(@ModelAttribute User user) {
	    userRepo.save(user); // save user to DB
	    return "redirect:/bank/login"; // go to login page
	}

	
	@GetMapping("/register-pages")
	public String registerPage(Model model){
		model.addAttribute("user", new User());
		return "register";// templates/register.html
	}
	
	@GetMapping("")
	public String home() {
	    return "index"; // maps to templates/index.html
	}
	
	@PostMapping("/login")
	public String loginUser(@ModelAttribute User user, Model model) {
	    // Find user by email
	    User existingUser = userRepo.findByEmail(user.getEmail());

	    if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
	        
	        // ✅ Check if this user already has an account
	    	Account account = accRepo.findByUser(existingUser).orElse(null);
	    	

	        if (account != null) {
	            // User already has an account → go to UserHome
	            model.addAttribute("user", existingUser);
	            model.addAttribute("account", account);
	            return "UserHome";  // maps to templates/UserHome.html
	        } 
	        else {
	            // User logged in but no account → redirect to create account page
	            model.addAttribute("user", existingUser);
	            model.addAttribute("account", new Account()); // empty form
	          //  return "CreateAccount"; // make a CreateAccount.html page
	            return "redirect:/bank/create-account?userId=" + existingUser.getUserId();
	        }
	    } 
	    else {
	        // ❌ Login failed
	        model.addAttribute("error", "Invalid email or password");
	        return "login"; // reload login.html
	    }
	}


	
	 // Show login page
    @GetMapping("/login")
    public String loginPage(Model model) {
    	model.addAttribute("user",new User());
    	return "login";  // corresponds to login.html in templates
    }

    // Show home/dashboard after login
    @GetMapping("/home")
    public String homePage() {
        return "index";   // corresponds to home.html
    }
    
    @GetMapping("/user-home")
    public String userHome(Model model) {
        model.addAttribute("user",new User());
        return "UserHome";  // maps to templates/UserHome.html
    }
    
    @PostMapping("/create-account")
    public String createAccount(@ModelAttribute Account account, @RequestParam Long userId, Model model) { 
    	
    	// Fetch the user linked with this account 
    	User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));// Add to model 
    	if (user == null) {
    	    return "redirect:/bank/register"; // or show error
    	}

    	account.setUser(user);
    	
    	// Save account
    	Account savedAccount = accRepo.save(account); 
    	 
    	model.addAttribute("user", user);
    	model.addAttribute("account", savedAccount); // ✅ Redirect to UserHome
    	return "UserHome";
    	}
    

    @GetMapping("/create-account")
    public String showCreateAccountPage(@RequestParam Long userId, Model model) {
        Account account = new Account();
        account.setAccount_balance(0.0);

        model.addAttribute("account", account);
        model.addAttribute("userId", userId); // Pass userId to form

        return "CreateAccount";
    }




    @PostMapping("/deposite")
    public String depositAmount(@RequestParam Long accountNumber, 
                                @RequestParam Double amount, 
                                Model model) {
        Account account = accRepo.findById(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        
        if (amount <= 0) { // ❌ Prevent negative or zero deposit
            model.addAttribute("account", account);
            model.addAttribute("error", "Invalid amount!");
            return "UserHome";
        }
        
        account.setAccount_balance(account.getAccount_balance() + amount);
        accRepo.save(account);

        model.addAttribute("account", account);
        model.addAttribute("message", "₹" + amount + " deposited successfully!");
        return "UserHome";
    }
    
    @GetMapping("/depositePage")
    public String showDepositPage(@RequestParam Long accountNumber, Model model) {
        Account account = accRepo.findById(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        model.addAttribute("account", account);
        return "Deposit";
    }

    @PostMapping("/withdraw")
    public String withdrawAmount(@RequestParam Long accountNumber, 
                                 @RequestParam Double amount, 
                                 Model model) {
        Account account = accRepo.findById(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        
        if (amount <= 0) { // ❌ Prevent negative or zero withdraw
            model.addAttribute("account", account);
            model.addAttribute("error", "Invalid amount!");
        } else if (account.getAccount_balance() < amount) {
            model.addAttribute("account", account);
            model.addAttribute("error", "Insufficient balance!");
        } else {
            account.setAccount_balance(account.getAccount_balance() - amount);
            accRepo.save(account);
            model.addAttribute("account", account);
            model.addAttribute("message", "₹" + amount + " withdrawn successfully!");
        }

        return "UserHome";
    }
 
 // Show withdraw page
    @GetMapping("/withdrawPage")
    public String showWithdrawPage(@RequestParam Long accountNumber, Model model) {
        // Fetch account by accountNumber
        Account account = accRepo.findById(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        
        // Add account to model to display details on page
        model.addAttribute("account", account);
        
        // Return Thymeleaf template name
        return "Withdraw";
    }
    
    @GetMapping("/logout")
    public String logoutUser(HttpSession session) {
        // Clear the session to remove logged-in user data
        session.invalidate();
        
        // Redirect to login page with a logout message
        return "redirect:/bank/login";
    }

}
