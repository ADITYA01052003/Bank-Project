package in.bank.main.entity;

import in.sp.main.entites.AccountStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Account {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long account_number;
	@Column
	private String account_holder_name;
	@Column
	private Double account_balance;
	
	@ManyToOne
	 @JoinColumn(name = "user_id") // foreign key column in Account table
	    private User user;

	    /* 
	       There are two other ways to link User and Account without @JoinColumn:

	       1️ Shared email approach:
	          - Keep 'email' field in both User and Account.
	          
	           private String userEmail; in user and account entity file
	          - Match accounts by comparing email strings.
	          - Simple but less efficient for queries and not normalized.

	       2️ Transient approach:
	          - Use @Transient in Account for temporary user reference (like email).
	          - Not stored in database, only used in runtime.
	          - Useful for calculations or temporary mapping, but not for persistent relationship.
	          
	          In account entity
	           @Transient
   			private User user;  // this won't be saved in DB 
	    */
	
	public Account() {
		//super();
		// TODO Auto-generated constructor stub
	}

	public Account(String account_holder_name, double account_balance) {
		//super();
		this.account_holder_name = account_holder_name;
		this.account_balance = account_balance;
	}
	
	public long getAccount_number() {
		return account_number;
	}
	public void setAccount_number(long account_number) {
		this.account_number = account_number;
	}
	public String getAccount_holder_name() {
		return account_holder_name;
	}
	public void setAccount_holder_name(String account_holder_name) {
		this.account_holder_name = account_holder_name;
	}
	public Double getAccount_balance() {
		return account_balance;
	}
	public void setAccount_balance(double account_balance) {
		this.account_balance = account_balance;
	}
	
	
	
		public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

		@Override
	public String toString() {
	    return "Account [account_number=" + account_number 
	            + ", account_holder_name=" + account_holder_name
	            + ", account_balance=" + account_balance 
	            + "]";
	}

	// Optional: getter for JSON/DTO
	public Long getUserId() {
	    return user != null ? user.getUserId() : null;
	}

	
	
}
