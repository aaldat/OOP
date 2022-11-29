package libraryMgmt;

import java.util.HashMap;
import java.util.Map;

public class User {
	
	private String name;
	private int maxNofBooks, duration, loanBooks = 0;
	private Map<Integer, Loan> loans = new HashMap<>();

	public User(String name, int maxNofBooks, int duration) {
		this.name = name;
		this.maxNofBooks = maxNofBooks;
		this.duration = duration;
	}

	public Map<Integer, Loan> getLoans() {
		return loans;
	}

	public Loan getLoan(int loanIndex) {
		return loans.get(loanIndex);
	}
	
	public void addLoan(int loanIndex, Loan l) {
		loans.put(loanIndex, l);
	}
	
	public String getName() {
		return name;
	}

	public int getMaxNofBooks() {
		return maxNofBooks;
	}

	public int getDuration() {
		return duration;
	}
	
	public int getLoanBooks() {
		return loanBooks;
	}
	
	public void rmBooks() {
		maxNofBooks--;
		loanBooks++;
	}
	
	public void addBooks() {
		maxNofBooks++;
		loanBooks--;
	}
}
